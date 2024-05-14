package com.example.demo.infraestructure.persistence;

import com.example.demo.domain.entity.Reproduction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReproductionPersistence {
    List<Reproduction> getAllReproductions();
    Optional<Reproduction> getReproductionById(Long reproductionId);
    List<Reproduction> getReproductionsByUserName(String reproductionUserName);
    Reproduction saveReproduction(Reproduction reproduction);
    void deleteReproduction(Long reproductionId);
    Page<Reproduction> findAll(Pageable pageable, String filter);
}
