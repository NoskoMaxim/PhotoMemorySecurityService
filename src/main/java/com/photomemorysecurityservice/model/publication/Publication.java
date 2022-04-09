package com.photomemorysecurityservice.model.publication;

import com.photomemorysecurityservice.model.user.User;

import javax.persistence.*;

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
            name = "id",
            unique = true,
            updatable = false
    )
    private Long id;

    @Column(
            name = "file_name",
            unique = true,
            length = 20
    )
    private String fileName;

    @Column(
            name = "file",
            unique = true
    )
    private byte[] file;

    @Column(
            name = "text",
            columnDefinition = "TEXT"
    )
    private String text;

    @ManyToOne
    @JoinColumn(
            name = "id",
            insertable = false,
            updatable = false
    )
    private User user;

    public Publication(
            Long id,
            String fileName,
            byte[] file,
            String text,
            User user) {
        this.id = id;
        this.fileName = fileName;
        this.file = file;
        this.text = text;
        this.user = user;
    }

    public Publication() {
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}