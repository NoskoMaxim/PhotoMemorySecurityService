package com.photomemorysecurityservice.repository.publication;

import com.photomemorysecurityservice.model.publication.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepos extends JpaRepository<Publication, Long> {

}