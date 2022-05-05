package com.photomemorysecurityservice.model.publication;

import com.photomemorysecurityservice.model.user.User;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity(
        name = "Publication"
)
@Table(
        name = "publication",
        schema = "public"
)
public class Publication {
    @Id
    @SequenceGenerator(
            name = "publication_sequence",
            sequenceName = "publication_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "publication_sequence"
    )
    @Column(
            name = "publication_id",
            unique = true,
            updatable = false
    )
    private Long publicationId;

    @Column(
            name = "file_name",
            unique = true,
            length = 20
    )
    private String fileName;

    @Column(
            name = "file_url",
            unique = true,
            columnDefinition = "TEXT",
            length = 500
    )
    private String fileURL;

    @Column(
            name = "size_file",
            nullable = false
    )
    private Long sizeFile;

    @Column(
            name = "format",
            nullable = false
    )
    private String format;

    @Column(
            name = "text",
            columnDefinition = "TEXT"
    )
    private String text;

    @ManyToOne(fetch = LAZY)
    private User user;

    @OneToMany(
            mappedBy = "publication",
            fetch = EAGER
    )
    private List<Comments> comments = new ArrayList<>();

    public Publication(
            Long publicationId,
            String fileName,
            String fileURL,
            Long sizeFile,
            String format,
            String text,
            User user,
            List<Comments> comments) {
        this.publicationId = publicationId;
        this.fileName = fileName;
        this.fileURL = fileURL;
        this.sizeFile = sizeFile;
        this.format = format;
        this.text = text;
        this.user = user;
        this.comments = comments;
    }

    public Publication() {
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}