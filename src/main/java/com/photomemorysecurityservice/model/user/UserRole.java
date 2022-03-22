package com.photomemorysecurityservice.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(
        name = "UserRole"
)
@Table(
        name = "role",
        schema = "public"
)
public class UserRole {

    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "role_sequence"
    )
    @Column(
            name = "id",
            unique = true,
            updatable = false
    )
    @PrimaryKeyJoinColumn
    private Long roleId;

    @Column(
            name = "role_name",
            unique = true,
            columnDefinition = "TEXT"
    )
    private String roleName;

    public UserRole(
            Long roleId,
            String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public UserRole() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
