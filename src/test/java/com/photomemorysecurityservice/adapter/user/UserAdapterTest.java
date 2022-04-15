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
        assertEquals(1L, actual.getUserId());
        assertEquals("Max", actual.getUsername());
        assertEquals("Maxim", actual.getFirstName());
        assertEquals("MaxMax", actual.getLastName());
        assertEquals("Max@haha.com", actual.getEmail());
        assertEquals("+111111", actual.getPhone());
        actual.getRoles().forEach(role -> {
            assertEquals(1L, role.getRoleId());
            assertEquals("USER", role.getRoleName());
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
            assertEquals(1L, actual.getUserId());
            assertEquals("Max", actual.getUsername());
            assertEquals("Maxim", actual.getFirstName());
            assertEquals("MaxMax", actual.getLastName());
            assertEquals("Max@haha.com", actual.getEmail());
            assertEquals("+111111", actual.getPhone());
            actual.getRoles().forEach(role -> {
                assertEquals(1L, role.getRoleId());
                assertEquals("USER", role.getRoleName());
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
        assertNotEquals(2L, actual.getUserId());
        assertNotEquals("NoMax", actual.getUsername());
        assertNotEquals("NoMaxim", actual.getFirstName());
        assertNotEquals("NoMaxMax", actual.getLastName());
        assertNotEquals("NoMax@haha.com", actual.getEmail());
        assertNotEquals("+No111111", actual.getPhone());
        actual.getRoles().forEach(role -> {
            assertNotEquals(2L, role.getRoleId());
            assertNotEquals("ADMIN", role.getRoleName());
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
            assertNotEquals(2L, actual.getUserId());
            assertNotEquals("NoMax", actual.getUsername());
            assertNotEquals("NoMaxim", actual.getFirstName());
            assertNotEquals("NoMaxMax", actual.getLastName());
            assertNotEquals("NoMax@haha.com", actual.getEmail());
            assertNotEquals("No+111111", actual.getPhone());
            actual.getRoles().forEach(role -> {
                assertNotEquals(2L, role.getRoleId());
                assertNotEquals("ADMIN", role.getRoleName());
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
        assertEquals(1L, actual.getUserId());
        assertEquals("Max", actual.getUsername());
        assertNull(actual.getPassword());
        assertEquals("Maxim", actual.getFirstName());
        assertEquals("MaxMax", actual.getLastName());
        assertEquals("Max@haha.com", actual.getEmail());
        assertEquals("+111111", actual.getPhone());
        assertNull(actual.getRoles());
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
        assertNotEquals(2L, actual.getUserId());
        assertNotEquals("NoMax", actual.getUsername());
        assertNull(actual.getPassword());
        assertNotEquals("NoMaxim", actual.getFirstName());
        assertNotEquals("NoMaxMax", actual.getLastName());
        assertNotEquals("NoMax@haha.com", actual.getEmail());
        assertNotEquals("No+111111", actual.getPhone());
        assertNull(actual.getRoles());
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
        assertEquals(1L, actual.getRoleId());
        assertEquals("USER", actual.getRoleName());
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
            assertEquals(1L, actual.getRoleId());
            assertEquals("USER", actual.getRoleName());
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
        assertNotEquals(2L, actual.getRoleId());
        assertNotEquals("ADMIN", actual.getRoleName());
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
            assertNotEquals(2L, actual.getRoleId());
            assertNotEquals("ADMIN", actual.getRoleName());
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
        assertEquals(1L, actual.getRoleId());
        assertEquals("USER", actual.getRoleName());
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
        assertNotEquals(2L, actual.getRoleId());
        assertNotEquals("ADMIN", actual.getRoleName());
    }
}