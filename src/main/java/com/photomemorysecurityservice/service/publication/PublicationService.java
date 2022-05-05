package com.photomemorysecurityservice.service.publication;

import com.photomemorysecurityservice.dto.publication.CommentDto;
import com.photomemorysecurityservice.dto.publication.CommentForAddingToPublicationDto;
import com.photomemorysecurityservice.dto.publication.PublicationDto;
import com.photomemorysecurityservice.dto.publication.TextForCreatePublicationDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PublicationService {
    void createPublication(Long UserId, MultipartFile file) throws IOException;

    PublicationDto getPublication(Long id);

    List<PublicationDto> getAllPublications();

    List<PublicationDto> getAllPublicationByUserWithUserId(Long userId);

    void setTextForPublication(TextForCreatePublicationDto textDto);

    void setCommentForPublication(CommentForAddingToPublicationDto commentDto);

    void deletePublicationById(Long publicationId);
}