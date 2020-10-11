package com.gmail.olyagavrilova.onlinelibrary.service.mapper;

import com.gmail.olyagavrilova.onlinelibrary.dao.entity.User;
import com.gmail.olyagavrilova.onlinelibrary.model.UserDto;

public class UserMapper {

    public UserDto convertEntityToDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setUsername(entity.getUsername());
        userDto.setPassword(entity.getPassword());
        userDto.setRole(entity.getRole());
        userDto.setEnabled(entity.isEnabled());
        return userDto;
    }
}
