package com.photomemorysecurityservice.adapter.user;

import com.photomemorysecurityservice.dto.user.UserDto;
import com.photomemorysecurityservice.dto.user.UserRoleDto;
import com.photomemorysecurityservice.dto.user.UserToUpdateFormDto;
import com.photomemorysecurityservice.model.user.User;
import com.photomemorysecurityservice.model.user.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.modelmapper.convention.MatchingStrategies.LOOSE;

@Component
public class UserAdapter {

    ModelMapper userMapper = new ModelMapper();
    ModelMapper roleMapper = new ModelMapper();

    public UserAdapter() {
        this.userMapper.getConfiguration().setMatchingStrategy(LOOSE);
    }

    public UserDto getUserDto(User user) {
        return this.userMapper.map(user, UserDto.class);
    }

    public User getUser(UserToUpdateFormDto userDto) {
        return this.userMapper.map(userDto, User.class);
    }

    public List<UserDto> getUserDtoList(List<User> users) {
        return users.stream()
                .map(this::getUserDto)
                .collect(Collectors.toList());
    }

    public UserRoleDto getUserRoleDto(UserRole role) {
        return this.roleMapper.map(role, UserRoleDto.class);
    }

    public List<UserRoleDto> getUserRoleDtoList(List<UserRole> roles) {
        return roles.stream()
                .map(this::getUserRoleDto)
                .collect(Collectors.toList());
    }
}
