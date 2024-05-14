package com.example.demo.infraestructure.rest;

import com.example.demo.application.dto.UserDto;
import com.example.demo.application.service.AuthService;
import com.example.demo.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public UserController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        UserDto user = authService.getUser(username).orElseThrow();
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/users/{username}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto updatedUser) {
        try {
            UserDto updatedUserDto = userService.updateUser(updatedUser);
            return ResponseEntity.ok(updatedUserDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

