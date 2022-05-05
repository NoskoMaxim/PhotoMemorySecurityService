package com.photomemorysecurityservice.repository.comments;

import com.photomemorysecurityservice.model.publication.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepos extends JpaRepository<Comments, Long> {
}
