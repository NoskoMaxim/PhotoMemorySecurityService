package com.photomemorysecurityservice.service.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.photomemorysecurityservice.adapter.user.UserAdapter;
import com.photomemorysecurityservice.config.exception.exceptions.*;
import com.photomemorysecurityservice.dto.user.*;
import com.photomemorysecurityservice.model.user.User;
import com.photomemorysecurityservice.model.user.UserRole;
import com.photomemorysecurityservice.repository.role.RoleRepos;
import com.photomemorysecurityservice.repository.user.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepos userRepos;
    private final RoleRepos roleRepos;
    private final PasswordEncoder passwordEncoder;
    private final UserAdapter userAdapter;

    @Autowired
    public UserServiceImpl(UserRepos userRepos, RoleRepos roleRepos, PasswordEncoder passwordEncoder, UserAdapter userAdapter) {
        this.userRepos = userRepos;
        this.roleRepos = roleRepos;
        this.passwordEncoder = passwordEncoder;
        this.userAdapter = userAdapter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepos.findUserByUsername(username)
                .map(user -> {
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    user.getRoles().forEach(role -> authorities
                            .add(new SimpleGrantedAuthority(role.getRoleName())));
                    return new org.springframework.security.core.userdetails.User(
                            user.getUsername(),
                            user.getPassword(),
                            authorities);
                })
                .orElseThrow(() -> new UserNotFoundException(
                        new HashMap<>() {{
                            put("UserNotFoundException",
                                    "User with name: " + username + " not found");
                        }}, NO_CONTENT));
    }

    @Override
    public void createUser(UsernameAndPasswordToCreateFormDto createForm) {
        User user = new User();
        user.setUsername(createForm.getUsername());
        user.setPassword(passwordEncoder.encode(createForm.getPassword()));
        try {
            userRepos.save(user);
            user = userRepos.findUserByUsername(createForm.getUsername())
                    .orElseThrow(() -> new UserNotFoundException(
                            new HashMap<>() {{
                                put("UserNotFoundException",
                                        "User with name: " + createForm.getUsername() + " not found");
                            }}, NO_CONTENT));
            user.getRoles()
                    .add(roleRepos.findUserRoleByRoleName("USER")
                            .orElseThrow(() -> new UserRoleNotFoundException(
                                    new HashMap<>() {{
                                        put("UserRoleNotFoundException",
                                                "User role with name: USER not found");
                                    }}, NO_CONTENT))
                    );
            userRepos.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new UsernameExistenceException(new HashMap<>() {{
                put("UsernameExistenceException",
                        "Username: " + createForm.getUsername() + " already exists");
            }});
        }
    }

    @Override
    public void updateUser(UserToUpdateFormDto userDto) {
        User userFromDB = userRepos.findById(userDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(
                        new HashMap<>() {{
                            put("UserNotFoundException",
                                    "User with ID: " + userDto.getUserId() + " not found");
                        }}, NO_CONTENT));
        User user = userAdapter.getUser(userDto);
        user.setPassword(userFromDB.getPassword());
        user.setRoles(userFromDB.getRoles());
        userRepos.save(user);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userRepos.findUserByUsername(username)
                .map(userAdapter::getUserDto)
                .orElseThrow(() -> new UserNotFoundException(
                        new HashMap<>() {{
                            put("UserNotFoundException",
                                    "User with name: " + username + " not found");
                        }}, NO_CONTENT));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userAdapter.getUserDtoList(userRepos.findAll());
    }

    @Override
    public void createRole(String roleName) {
        UserRole role = new UserRole();
        role.setRoleName(roleName);
        try {
            roleRepos.save(role);
        } catch (DataIntegrityViolationException exception) {
            throw new RoleNameExistenceException(new HashMap<>() {{
                put("RoleNameExistenceException",
                        "Role with name: " + roleName + " already exists");
            }}, BAD_REQUEST);
        }
    }

    @Override
    public void updateRole(UserRoleDto roleDto) {
        roleRepos.save(userAdapter.getUserRole(roleDto));
    }

    @Override
    public void deleteRole(Long roleId) {
        try {
            roleRepos.deleteById(roleId);
        } catch (EmptyResultDataAccessException psqlException) {
            throw new UserRoleNotFoundException(new HashMap<>() {{
                put("UserRoleNotFoundException",
                        "Role with ID: " + roleId + " not found");
            }}, GONE);
        }
    }

    @Override
    public List<UserRoleDto> getAllRoles() {
        return userAdapter.getUserRoleDtoList(roleRepos.findAll());
    }

    @Override
    public void addRoleToUser(RoleToUserFormDto roleToUserFormDto) {
        User user = userRepos.findUserByUsername(roleToUserFormDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException(
                        new HashMap<>() {{
                            put("UserNotFoundException",
                                    "User with name: " + roleToUserFormDto.getUsername() + " not found");
                        }}, NO_CONTENT));
        UserRole role = roleRepos.findUserRoleByRoleName(roleToUserFormDto.getRoleName())
                .orElseThrow(() -> new UserRoleNotFoundException(
                        new HashMap<>() {{
                            put("UserRoleNotFoundException",
                                    "Role with name: " + roleToUserFormDto.getRoleName() + " not found");
                        }}, NO_CONTENT));
        user.getRoles().add(role);
        userRepos.save(user);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userRepos.findUserByUsername(username)
                        .orElseThrow(() -> new UserNotFoundException(
                                new HashMap<>() {{
                                    put("UserNotFoundException",
                                            "User with name: " + username + " not found");
                                }}, NO_CONTENT));
                String accessToken = createToken(request, algorithm, user);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (JWTVerificationException exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("access_token", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RefreshTokenExpirationException(new HashMap<>(){{
                put("RefreshTokenExpirationException",
                        "Refresh token is missing");
            }}, UNAUTHORIZED);
        }
    }

    private String createToken(HttpServletRequest request, Algorithm algorithm, User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 120 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getRoles().stream()
                        .map(UserRole::getRoleName).collect(Collectors.toList()))
                .sign(algorithm);
    }
}