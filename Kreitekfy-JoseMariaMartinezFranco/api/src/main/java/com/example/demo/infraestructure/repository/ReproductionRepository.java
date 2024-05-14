package com.example.demo.infraestructure.repository;

import com.example.demo.domain.entity.Reproduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReproductionRepository extends JpaRepository<Reproduction, Long>, JpaSpecificationExecutor<Reproduction> {

    List<Reproduction> findByUserName(String userName);
}
