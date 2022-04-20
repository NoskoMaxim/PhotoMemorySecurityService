package com.photomemorysecurityservice.repository.publication;

import com.photomemorysecurityservice.model.publication.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationRepos extends JpaRepository<Publication, Long> {
    Optional<Publication> findPublicationByFileName(String fileName);

    List<Publication> findPublicationsByUser_UserId(Long userId);
}