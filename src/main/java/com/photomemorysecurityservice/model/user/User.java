package com.photomemorysecurityservice.model.user;

import com.photomemorysecurityservice.model.publication.Publication;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity(
        name = "Student"
)
@Table(
        name = "users",
        schema = "public"
)
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id",
            unique = true,
            updatable = false
    )
    @PrimaryKeyJoinColumn
    private Long userId;

    @Column(
            name = "username",
            unique = true,
            length = 20
    )
    private String username;

    @Column(
            name = "password",
            unique = true
    )
    private String password;

    @Column(
            name = "first_name",
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name = "last_name",
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name = "email",
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name = "phone",
            columnDefinition = "TEXT"
    )
    private String phone;

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<UserRole> roles;

    @OneToMany(
            mappedBy = "user",
            fetch = LAZY
    )
    private List<Publication> publications;

    public User(
            Long userId,
            String username,
            String password,
            String firstName,
            String lastName,
            String email,
            String phone,
            List<UserRole> roles,
            List<Publication> publications) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
        this.publications = publications;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }
}
