package com.photomemorysecurityservice.dto.publication;

public class TextForCreatePublicationDto {
    private Long publicationId;
    private String text;

    public TextForCreatePublicationDto(Long publicationId, String text) {
        this.publicationId = publicationId;
        this.text = text;
    }

    public TextForCreatePublicationDto() {
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
