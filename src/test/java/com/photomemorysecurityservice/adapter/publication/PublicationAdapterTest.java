package com.photomemorysecurityservice.adapter.publication;

import com.photomemorysecurityservice.dto.publication.PublicationDto;
import com.photomemorysecurityservice.model.publication.Publication;
import com.photomemorysecurityservice.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PublicationAdapterTest {

    PublicationAdapter publicationAdapter;

    @BeforeEach
    void setUp() {
        publicationAdapter = new PublicationAdapter();
    }

    @Test
    void itShouldGetPhotoDto() {
        //Arrange
        User user = new User();
        user.setUsername("Max");
        Publication publication = new Publication(
                1L,
                "fileName",
                "URL",
                1000L,
                "pdf",
                "SameText",
                user
        );

        //Act
        PublicationDto actual = publicationAdapter.getPhotoDto(publication);

        //Assert
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getUsername()).isEqualTo("Max");
    }

    @Test
    void itShouldNotGetPhotoDto() {
        //Arrange
        User user = new User();
        user.setUsername("Max");
        Publication publication = new Publication(
                1L,
                "fileName",
                "URL",
                1000L,
                "pdf",
                "SameText",
                user
        );

        //Act
        PublicationDto actual = publicationAdapter.getPhotoDto(publication);

        //Assert
        assertThat(actual.getId()).isNotEqualTo(2L);
        assertThat(actual.getUsername()).isNotEqualTo("Maxim");
    }
}