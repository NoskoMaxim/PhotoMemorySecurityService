package com.photomemorysecurityservice.adapter.publication;

import com.photomemorysecurityservice.dto.publication.PublicationDto;
import com.photomemorysecurityservice.model.publication.Publication;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class PublicationAdapter {
    ModelMapper publicationMapper = new ModelMapper();

    public PublicationAdapter() {
        TypeMap<Publication, PublicationDto> propertyMapperPublicationToPublicationDto
                = this.publicationMapper.createTypeMap(Publication.class, PublicationDto.class);
        propertyMapperPublicationToPublicationDto.addMappings(publicationMapper ->
                publicationMapper.map(publication ->
                        publication.getUser().getUsername(), PublicationDto::setUsername));
    }

    public PublicationDto getPhotoDto(Publication publication) {
        return this.publicationMapper.map(publication, PublicationDto.class);
    }
}