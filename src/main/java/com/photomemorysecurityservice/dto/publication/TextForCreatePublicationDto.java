package com.photomemorysecurityservice.dto.publication;

public class TextForCreatePublicationDto {
    private String text;

    public TextForCreatePublicationDto(String text) {
        this.text = text;
    }

    public TextForCreatePublicationDto() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
