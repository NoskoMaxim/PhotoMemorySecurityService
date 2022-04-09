package com.photomemorysecurityservice.repository.role;

import com.photomemorysecurityservice.model.user.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleReposTest {

    @Autowired
    private RoleRepos underTestRepos;

    @AfterEach
    void tearDown() {
        underTestRepos.deleteAll();
    }

    @Test
    void itShouldFindUserRoleByName() {

        //Arrange
        UserRole role = new UserRole();
        role.setRoleName("VISITOR");
        underTestRepos.save(role);

        //Act
        Optional<UserRole> actual = underTestRepos
                .findUserRoleByRoleName("VISITOR");

        //Assert
        assertEquals("VISITOR", actual.get().getRoleName());
    }

    @Test
    void itShouldNotFindUserRoleByName() {
        //Act
        Optional<UserRole> expected = underTestRepos
                .findUserRoleByRoleName("VISITOR");

        //Assert
        assertThat(expected.isEmpty()).isTrue();
    }
}