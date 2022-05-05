package com.photomemorysecurityservice.service.publication;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.photomemorysecurityservice.adapter.publication.PublicationAdapter;
import com.photomemorysecurityservice.config.exception.exceptions.CloudinaryException;
import com.photomemorysecurityservice.config.exception.exceptions.PhotoNameExistenceException;
import com.photomemorysecurityservice.config.exception.exceptions.PublicationNotFoundException;
import com.photomemorysecurityservice.config.exception.exceptions.UserNotFoundException;
import com.photomemorysecurityservice.dto.publication.CommentForAddingToPublicationDto;
import com.photomemorysecurityservice.dto.publication.PublicationDto;
import com.photomemorysecurityservice.dto.publication.TextForCreatePublicationDto;
import com.photomemorysecurityservice.model.publication.Comments;
import com.photomemorysecurityservice.model.publication.Publication;
import com.photomemorysecurityservice.model.user.User;
import com.photomemorysecurityservice.repository.comments.CommentsRepos;
import com.photomemorysecurityservice.repository.publication.PublicationRepos;
import com.photomemorysecurityservice.repository.user.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.photomemorysecurityservice.config.cloudinary.CloudinaryConfig.getCloudinaryConfiguration;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Service
@Transactional
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepos publicationRepos;
    private final UserRepos userRepos;
    private final PublicationAdapter publicationAdapter;
    private final CommentsRepos commentsRepos;

    @Autowired
    public PublicationServiceImpl(
            PublicationRepos publicationRepos,
            UserRepos userRepos,
            PublicationAdapter publicationAdapter, CommentsRepos commentsRepos) {
        this.publicationRepos = publicationRepos;
        this.userRepos = userRepos;
        this.publicationAdapter = publicationAdapter;
        this.commentsRepos = commentsRepos;
    }

    @Override
    public void createPublication(Long userId, MultipartFile multipartFile) throws IOException {
        Cloudinary cloudinary = new Cloudinary(getCloudinaryConfiguration());
        String public_id = null;
        try {
            Map cloudinaryResponse = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
            User user = userRepos.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(
                            new HashMap<>() {{
                                put("UserNotFoundException",
                                        "User with ID: " + userId + " not found");
                            }}, NO_CONTENT));
            Publication publication = new Publication();
            publication.setFileName(cloudinaryResponse.get("public_id").toString());
            publication.setFileURL(cloudinaryResponse.get("secure_url").toString());
            publication.setSizeFile(multipartFile.getSize());
            publication.setFormat(cloudinaryResponse.get("format").toString());
            publication.setUser(user);
            public_id = cloudinaryResponse.get("public_id").toString();
            publicationRepos.save(publication);
        } catch (DataIntegrityViolationException DBException) {
            cloudinary.uploader().destroy(public_id, ObjectUtils.emptyMap());
            throw new PhotoNameExistenceException(new HashMap<>() {{
                put("PhotoNameExistenceException",
                        "Photo name already exists");
            }});
        } catch (IOException exception) {
            throw new CloudinaryException(new HashMap<>() {{
                put("CloudinaryException",
                        exception.getMessage());
            }});
        }
    }

    @Override
    public PublicationDto getPublication(Long id) {
        return publicationRepos.findById(id)
                .map(publicationAdapter::getPublicationDto)
                .orElseThrow(() -> new PublicationNotFoundException(
                        new HashMap<>() {{
                            put("PublicationNotFoundException",
                                    "Publication with ID: " + id + " not found");
                        }}, NO_CONTENT));
    }

    @Override
    public List<PublicationDto> getAllPublications() {
        return publicationAdapter.getPublicationDtoList(publicationRepos.findAll());
    }

    @Override
    public List<PublicationDto> getAllPublicationByUserWithUserId(Long userId) {
        return publicationAdapter
                .getPublicationDtoList(publicationRepos
                        .findPublicationsByUser_UserId(userId)
                );
    }

    @Override
    public void setTextForPublication(TextForCreatePublicationDto textDto) {
        Publication publication = publicationRepos.findById(textDto.getPublicationId())
                .orElseThrow(() -> new PublicationNotFoundException(
                        new HashMap<>() {{
                            put("PublicationNotFoundException",
                                    "Publication with ID: " + textDto.getPublicationId() + " not found");
                        }}, NO_CONTENT));
        publication.setText(textDto.getText());
        publicationRepos.save(publication);
    }

    @Override
    public void setCommentForPublication(CommentForAddingToPublicationDto commentDto) {
        Publication publication = publicationRepos.findById(commentDto.getPublicationId())
                .orElseThrow(() -> new PublicationNotFoundException(
                        new HashMap<>() {{
                            put("PublicationNotFoundException",
                                    "Publication with ID: " + commentDto.getPublicationId() + " not found");
                        }}, NO_CONTENT));
        User user = userRepos.findById(commentDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(
                        new HashMap<>() {{
                            put("UserNotFoundException",
                                    "User with ID: " + commentDto.getUserId() + " not found");
                        }}, NO_CONTENT));
        Comments comment = new Comments();
        comment.setUser(user);
        comment.setComment(commentDto.getText());
        comment.setPublication(publication);
        commentsRepos.save(comment);
    }

    @Override
    public void deletePublicationById(Long publicationId) {
        publicationRepos.deleteById(publicationId);
    }
}