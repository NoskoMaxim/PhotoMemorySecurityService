package com.photomemorysecurityservice.adapter.publication;

import com.photomemorysecurityservice.dto.publication.CommentDto;
import com.photomemorysecurityservice.dto.publication.PublicationDto;
import com.photomemorysecurityservice.model.publication.Comments;
import com.photomemorysecurityservice.model.publication.Publication;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.modelmapper.convention.MatchingStrategies.LOOSE;

@Component
public class PublicationAdapter {
    ModelMapper publicationMapper = new ModelMapper();

    public PublicationAdapter() {
        this.publicationMapper.getConfiguration().setMatchingStrategy(LOOSE);
        TypeMap<Publication, PublicationDto> propertyMapperPublicationToPublicationDto
                = this.publicationMapper.createTypeMap(Publication.class, PublicationDto.class);
        propertyMapperPublicationToPublicationDto.addMappings(publicationMapper ->
                publicationMapper.map(publication ->
                        publication.getUser().getUsername(), PublicationDto::setUsername));
        TypeMap<Comments, CommentDto> propertyMapperCommentsToCommentsDto
                = this.publicationMapper.createTypeMap(Comments.class, CommentDto.class);
        propertyMapperCommentsToCommentsDto.addMappings(publicationMapper -> {
            publicationMapper.map(comments ->
                    comments.getUser().getUsername(), CommentDto::setUsername);
        });
    }

    public PublicationDto getPublicationDto(Publication publication) {
        return this.publicationMapper.map(publication, PublicationDto.class);
    }

    public List<PublicationDto> getPublicationDtoList(List<Publication> publications) {
        return publications.stream()
                .map(this::getPublicationDto)
                .collect(Collectors.toList());
    }
}