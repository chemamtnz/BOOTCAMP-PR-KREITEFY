package com.example.demo.application.service;

import com.example.demo.application.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDto> getUsersById(String userId);
    UserDto saveUser(UserDto user);
    public UserDto updateUser(UserDto userDto);

}
