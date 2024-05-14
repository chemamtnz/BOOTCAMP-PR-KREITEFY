package com.example.demo.application.service.impl;

import com.example.demo.application.dto.UserDto;
import com.example.demo.application.service.UserService;
import com.example.demo.domain.entity.User;
import com.example.demo.infraestructure.mapper.UserMapper;
import com.example.demo.infraestructure.persistence.UserPersistence;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserPersistence persistence;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(@Qualifier("userPersistenceImpl") UserPersistence persistence, UserMapper mapper, PasswordEncoder passwordEncoder, PasswordEncoder passwordEncoder1) {
        this.persistence = persistence;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder1;
    }

    @Override
    public Optional<UserDto> getUsersById(String userId) {
        return this.persistence.getUsersById(userId).map(mapper::toDto);
    }
    @Override
    public UserDto saveUser(UserDto userDTO) {
        System.out.println("Guardando usuario: " + userDTO);
        User user = this.persistence.saveUser(this.mapper.toEntity(userDTO));
        System.out.println("Usuario guardado: " + user);
        return this.mapper.toDto(user);
    }

    @Override
    public UserDto updateUser(UserDto updatedUser) {
        Optional<User> optionalUser = persistence.find(updatedUser.getUsername());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());

            if (updatedUser.getPassword().equals(user.getPassword()) || passwordEncoder.matches(updatedUser.getPassword(),
                    user.getPassword())) {
                user.setPassword(user.getPassword());
            } else {
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            user.setRole(updatedUser.getRole());

            User savedUser = persistence.save(user);
            return mapper.toDto(savedUser);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }
}
