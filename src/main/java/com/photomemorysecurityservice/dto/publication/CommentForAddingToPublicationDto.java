package com.photomemorysecurityservice.dto.publication;

public class CommentForAddingToPublicationDto {
    Long publicationId;
    Long userId;
    String text;

    public CommentForAddingToPublicationDto(Long publicationId, Long userId, String text) {
        this.publicationId = publicationId;
        this.userId = userId;
        this.text = text;
    }

    public CommentForAddingToPublicationDto() {
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
