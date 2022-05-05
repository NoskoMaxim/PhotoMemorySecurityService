package com.photomemorysecurityservice.dto.publication;

public class CommentDto {
    Long commentId;
    String comment;
    String username;

    public CommentDto(Long commentId, String comment, String username) {
        this.commentId = commentId;
        this.comment = comment;
        this.username = username;
    }

    public CommentDto() {
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}