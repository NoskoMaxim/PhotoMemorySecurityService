package com.photomemorysecurityservice.dto;

public record OperationMessageDto(String text) {
    public String getText() {
        return text;
    }
}