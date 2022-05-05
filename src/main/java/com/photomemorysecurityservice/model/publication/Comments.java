package com.photomemorysecurityservice.model.publication;

import com.photomemorysecurityservice.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity(
        name = "Comments"
)
@Table(
        name = "comments",
        schema = "public"
)
public class Comments {
    @Id
    @SequenceGenerator(
            name = "comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "comment_sequence"
    )
    @Column(
            name = "comment_id",
            unique = true,
            updatable = false
    )
    private Long commentId;

    @Column(
            name = "comment",
            unique = true,
            length = 500
    )
    private String comment;

    @ManyToOne(fetch = LAZY)
    private Publication publication;

    @ManyToOne(fetch = LAZY)
    private User user;

    public Comments(
            Long commentId,
            String comment,
            Publication publication,
            User user) {
        this.commentId = commentId;
        this.comment = comment;
        this.publication = publication;
        this.user = user;
    }

    public Comments() {
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentsId) {
        this.commentId = commentsId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
