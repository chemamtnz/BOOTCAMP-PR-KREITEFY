package com.example.demo.infraestructure.repository;

import com.example.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

}
