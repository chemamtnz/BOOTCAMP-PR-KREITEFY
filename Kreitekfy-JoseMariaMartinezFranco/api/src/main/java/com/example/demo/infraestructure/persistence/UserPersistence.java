package com.example.demo.infraestructure.persistence;

import com.example.demo.application.dto.UserDto;
import com.example.demo.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserPersistence {
    User save(User user);
    Optional<User> find(String username);
    Optional<User> getUsersById(String userId);
    User saveUser(User user);
}
