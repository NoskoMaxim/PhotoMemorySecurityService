package com.photomemorysecurityservice.controller.user;

import com.photomemorysecurityservice.dto.OperationMessageDto;
import com.photomemorysecurityservice.dto.user.RoleToUserFormDto;
import com.photomemorysecurityservice.dto.user.UserDto;
import com.photomemorysecurityservice.dto.user.UserRoleDto;
import com.photomemorysecurityservice.dto.user.UserToUpdateFormDto;
import com.photomemorysecurityservice.dto.user.UsernameAndPasswordToCreateFormDto;
import com.photomemorysecurityservice.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/photomemory")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user/create",
            consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<OperationMessageDto> createUser(@RequestBody UsernameAndPasswordToCreateFormDto createForm) {
        userService.createUser(createForm);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @PutMapping(value = "/user/update",
            consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<OperationMessageDto> updateUser(@RequestBody UserToUpdateFormDto userDto) {
        userService.updateUser(userDto);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @GetMapping(value = "/user/get/all",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping(value = "/user/get/{username}",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }

    @PostMapping(value = "/role/create")
    public ResponseEntity<OperationMessageDto> createRole(@RequestParam String roleName) {
        userService.createRole(roleName);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @PostMapping(value = "/role/add",
            consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<OperationMessageDto> addRoleToUser(@RequestBody RoleToUserFormDto roleForm) {
        userService.addRoleToUser(roleForm);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @PutMapping(value = "/role/update",
            consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<OperationMessageDto> updateRole(@RequestBody UserRoleDto roleDto) {
        userService.updateRole(roleDto);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @DeleteMapping(value = "/role/delete/{roleId}")
    public ResponseEntity<OperationMessageDto> deleteRole(@PathVariable Long roleId) {
        userService.deleteRole(roleId);
        return ResponseEntity.ok(new OperationMessageDto("Successful operation"));
    }

    @GetMapping(value = "/role/get/all",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UserRoleDto>> getAllRoles() {
        return ResponseEntity.ok().body(userService.getAllRoles());
    }

    @GetMapping(value = "/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.refreshToken(request, response);
    }
}