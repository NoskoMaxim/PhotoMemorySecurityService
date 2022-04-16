package com.photomemorysecurityservice.adapter.user;

import com.photomemorysecurityservice.dto.user.UserDto;
import com.photomemorysecurityservice.dto.user.UserRoleDto;
import com.photomemorysecurityservice.dto.user.UserToUpdateFormDto;
import com.photomemorysecurityservice.model.user.User;
import com.photomemorysecurityservice.model.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserAdapterTest {

    UserAdapter userAdapter;

    @BeforeEach
    void setUp() {
        userAdapter = new UserAdapter();
    }

    @Test
    void itShouldGetUserDto() {
        //Arrange
        User user = new User(
                1L,
                "Max",
                "password",
                "Maxim",
                "MaxMax",
                "Max@haha.com",
                "+111111",
                List.of(
                        new UserRole(
                                1L,
                                "USER"
                        ),
                        new UserRole(
                                1L,
                                "USER"
                        )
                ),
                new ArrayList<>());

        //Act
        UserDto actual = userAdapter.getUserDto(user);

        //Assert
        assertThat(actual.getUserId()).isEqualTo(1L);
        assertThat(actual.getUsername()).isEqualTo("Max");
        assertThat(actual.getFirstName()).isEqualTo("Maxim");
        assertThat(actual.getLastName()).isEqualTo("MaxMax");
        assertThat(actual.getEmail()).isEqualTo("Max@haha.com");
        assertThat(actual.getPhone()).isEqualTo("+111111");
        actual.getRoles().forEach(role -> {
            assertThat(role.getRoleId()).isEqualTo(1L);
            assertThat(role.getRoleName()).isEqualTo("USER");
        });
    }

    @Test
    void itShouldGetUserDtoList() {
        //Arrange
        List<User> users = List.of(
                new User(
                        1L,
                        "Max",
                        "password",
                        "Maxim",
                        "MaxMax",
                        "Max@haha.com",
                        "+111111",
                        List.of(
                                new UserRole(
                                        1L,
                                        "USER"
                                ),
                                new UserRole(
                                        1L,
                                        "USER"
                                )
                        ),
                        new ArrayList<>()),
                new User(
                        1L,
                        "Max",
                        "password",
                        "Maxim",
                        "MaxMax",
                        "Max@haha.com",
                        "+111111",
                        List.of(
                                new UserRole(
                                        1L,
                                        "USER"
                                ),
                                new UserRole(
                                        1L,
                                        "USER"
                                )
                        ),
                        new ArrayList<>())
        );

        //Act
        List<UserDto> actualList = userAdapter.getUserDtoList(users);

        //Assert
        actualList.forEach(actual -> {
            assertThat(actual.getUserId()).isEqualTo(1L);
            assertThat(actual.getUsername()).isEqualTo("Max");
            assertThat(actual.getFirstName()).isEqualTo("Maxim");
            assertThat(actual.getLastName()).isEqualTo("MaxMax");
            assertThat(actual.getEmail()).isEqualTo("Max@haha.com");
            assertThat(actual.getPhone()).isEqualTo("+111111");
            actual.getRoles().forEach(role -> {
                assertThat(role.getRoleId()).isEqualTo(1L);
                assertThat(role.getRoleName()).isEqualTo("USER");
            });
        });
    }

    @Test
    void itShouldNotGetUserDto() {
        //Arrange
        User user = new User(
                1L,
                "Max",
                "password",
                "Maxim",
                "MaxMax",
                "Max@haha.com",
                "+111111",
                List.of(
                        new UserRole(
                                1L,
                                "USER"
                        ),
                        new UserRole(
                                1L,
                                "USER"
                        )
                ),
                new ArrayList<>());

        //Act
        UserDto actual = userAdapter.getUserDto(user);

        //Assert
        assertThat(actual.getUserId()).isNotEqualTo(2L);
        assertThat(actual.getUsername()).isNotEqualTo("NoMax");
        assertThat(actual.getFirstName()).isNotEqualTo("NoMaxim");
        assertThat(actual.getLastName()).isNotEqualTo("NoMaxMax");
        assertThat(actual.getEmail()).isNotEqualTo("NoMax@haha.com");
        assertThat(actual.getPhone()).isNotEqualTo("No+111111");
        actual.getRoles().forEach(role -> {
            assertThat(role.getRoleId()).isNotEqualTo(2L);
            assertThat(role.getRoleName()).isNotEqualTo("ADMIN");
        });
    }

    @Test
    void itShouldNotGetUserDtoList() {
        //Arrange
        List<User> users = List.of(
                new User(
                        1L,
                        "Max",
                        "password",
                        "Maxim",
                        "MaxMax",
                        "Max@haha.com",
                        "+111111",
                        List.of(
                                new UserRole(
                                        1L,
                                        "USER"
                                ),
                                new UserRole(
                                        1L,
                                        "USER"
                                )
                        ),
                        new ArrayList<>()),
                new User(
                        1L,
                        "Max",
                        "password",
                        "Maxim",
                        "MaxMax",
                        "Max@haha.com",
                        "+111111",
                        List.of(
                                new UserRole(
                                        1L,
                                        "USER"
                                ),
                                new UserRole(
                                        1L,
                                        "USER"
                                )
                        ),
                        new ArrayList<>())
        );

        //Act
        List<UserDto> actualList = userAdapter.getUserDtoList(users);

        //Assert
        actualList.forEach(actual -> {
            assertThat(actual.getUserId()).isNotEqualTo(2L);
            assertThat(actual.getUsername()).isNotEqualTo("NoMax");
            assertThat(actual.getFirstName()).isNotEqualTo("NoMaxim");
            assertThat(actual.getLastName()).isNotEqualTo("NoMaxMax");
            assertThat(actual.getEmail()).isNotEqualTo("NoMax@haha.com");
            assertThat(actual.getPhone()).isNotEqualTo("No+111111");
            actual.getRoles().forEach(role -> {
                assertThat(role.getRoleId()).isNotEqualTo(2L);
                assertThat(role.getRoleName()).isNotEqualTo("ADMIN");
            });
        });
    }

    @Test
    void itShouldGetUser() {
        //Arrange
        UserToUpdateFormDto userDto = new UserToUpdateFormDto(
                1L,
                "Max",
                "Maxim",
                "MaxMax",
                "Max@haha.com",
                "+111111"
        );

        //Act
        User actual = userAdapter.getUser(userDto);

        //Assert
        assertThat(actual.getUserId()).isEqualTo(1L);
        assertThat(actual.getUsername()).isEqualTo("Max");
        assertThat(actual.getFirstName()).isEqualTo("Maxim");
        assertThat(actual.getLastName()).isEqualTo("MaxMax");
        assertThat(actual.getEmail()).isEqualTo("Max@haha.com");
        assertThat(actual.getPhone()).isEqualTo("+111111");
        assertThat(actual.getRoles()).isEqualTo(new ArrayList<>());
    }

    @Test
    void itShouldNotGetUser() {
        //Arrange
        UserToUpdateFormDto userDto = new UserToUpdateFormDto(
                1L,
                "Max",
                "Maxim",
                "MaxMax",
                "Max@haha.com",
                "+111111"
        );

        //Act
        User actual = userAdapter.getUser(userDto);

        //Assert
        assertThat(actual.getUserId()).isNotEqualTo(2L);
        assertThat(actual.getUsername()).isNotEqualTo("NoMax");
        assertThat(actual.getFirstName()).isNotEqualTo("NoMaxim");
        assertThat(actual.getLastName()).isNotEqualTo("NoMaxMax");
        assertThat(actual.getEmail()).isNotEqualTo("NoMax@haha.com");
        assertThat(actual.getPhone()).isNotEqualTo("No+111111");
        assertThat(actual.getRoles()).isEqualTo(new ArrayList<>());
        assertThat(actual.getPassword()).isNull();
    }

    @Test
    void itShouldGetUserRoleDto() {
        //Arrange
        UserRole userRole = new UserRole(
                1L,
                "USER"
        );

        //Act
        UserRoleDto actual = userAdapter.getUserRoleDto(userRole);

        //Assert
        assertThat(actual.getRoleId()).isEqualTo(1L);
        assertThat(actual.getRoleName()).isEqualTo("USER");
    }

    @Test
    void itShouldGetUserRoleDtoList() {
        //Arrange
        List<UserRole> userRoles = List.of(
                new UserRole(
                        1L,
                        "USER"
                ),
                new UserRole(
                        1L,
                        "USER"
                )
        );

        //Act
        List<UserRoleDto> actualList = userAdapter.getUserRoleDtoList(userRoles);

        //Assert
        actualList.forEach(actual -> {
            assertThat(actual.getRoleId()).isEqualTo(1L);
            assertThat(actual.getRoleName()).isEqualTo("USER");
        });
    }

    @Test
    void itShouldNotGetUserRoleDto() {
        //Arrange
        UserRole userRole = new UserRole(
                1L,
                "USER"
        );

        //Act
        UserRoleDto actual = userAdapter.getUserRoleDto(userRole);

        //Assert
        assertThat(actual.getRoleId()).isNotEqualTo(2L);
        assertThat(actual.getRoleName()).isNotEqualTo("ADMIN");
    }

    @Test
    void itShouldNotGetUserRoleDtoList() {
        //Arrange
        List<UserRole> userRoles = List.of(
                new UserRole(
                        1L,
                        "USER"
                ),
                new UserRole(
                        1L,
                        "USER"
                )
        );

        //Act
        List<UserRoleDto> actualList = userAdapter.getUserRoleDtoList(userRoles);

        //Assert
        actualList.forEach(actual -> {
            assertThat(actual.getRoleId()).isNotEqualTo(2L);
            assertThat(actual.getRoleName()).isNotEqualTo("ADMIN");
        });
    }

    @Test
    void itShouldGetUserRole() {
        //Arrange
        UserRoleDto userRole = new UserRoleDto();
        userRole.setRoleId(1L);
        userRole.setRoleName("USER");

        //Act
        UserRole actual = userAdapter.getUserRole(userRole);

        //Assert
        assertThat(actual.getRoleId()).isEqualTo(1L);
        assertThat(actual.getRoleName()).isEqualTo("USER");
    }

    @Test
    void itShouldNotGetUserRole() {
        //Arrange
        UserRoleDto userRole = new UserRoleDto();
        userRole.setRoleId(1L);
        userRole.setRoleName("USER");

        //Act
        UserRole actual = userAdapter.getUserRole(userRole);

        //Assert
        assertThat(actual.getRoleId()).isNotEqualTo(2L);
        assertThat(actual.getRoleName()).isNotEqualTo("ADMIN");
    }
}