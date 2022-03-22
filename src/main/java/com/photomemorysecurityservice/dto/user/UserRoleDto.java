package com.photomemorysecurityservice.dto.user;

public class UserRoleDto {

    private Long roleId;
    private String roleName;

    public UserRoleDto(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public UserRoleDto() {
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