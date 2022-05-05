package com.photomemorysecurityservice.dto.publication;

import java.util.ArrayList;
import java.util.List;

public class PublicationDto {
    private Long id;
    private String fileName;
    private String fileURL;
    private Long sizeFile;
    private String format;
    private String text;
    private String username;
    private List<CommentDto> comments = new ArrayList<>();

    public PublicationDto(
            Long id,
            String fileName,
            String fileURL,
            Long sizeFile,
            String format,
            String text,
            String username,
            List<CommentDto> comments) {
        this.id = id;
        this.fileName = fileName;
        this.fileURL = fileURL;
        this.sizeFile = sizeFile;
        this.format = format;
        this.text = text;
        this.username = username;
        this.comments = comments;
    }

    public PublicationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public Long getSizeFile() {
        return sizeFile;
    }

    public void setSizeFile(Long sizeFile) {
        this.sizeFile = sizeFile;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<CommentDto> getCommentsDto() {
        return comments;
    }

    public void setCommentsDto(List<CommentDto> comments) {
        this.comments = comments;
    }
}