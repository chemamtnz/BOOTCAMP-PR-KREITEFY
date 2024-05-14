package com.example.demo.infraestructure.persistenceImpl;

import com.example.demo.domain.entity.Song;
import com.example.demo.domain.entity.User;
import com.example.demo.infraestructure.persistence.UserPersistence;
import com.example.demo.infraestructure.repository.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserPersistenceImpl implements UserPersistence {

    private final UserJpaRepository userJpaRepository;

    public UserPersistenceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override public Optional<User> find(String username) {
        return userJpaRepository.findById(username);
    }
    @Override
    public Optional<User> getUsersById(String userId) {
        return this.userJpaRepository.findById(userId);
    }
    @Override
    public User saveUser(User user) {
        return this.userJpaRepository.save(user);
    }


}
