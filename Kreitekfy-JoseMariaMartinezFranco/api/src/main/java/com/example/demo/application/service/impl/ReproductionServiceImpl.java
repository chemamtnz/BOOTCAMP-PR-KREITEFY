package com.example.demo.application.service.impl;

import com.example.demo.application.service.ReproductionService;
import com.example.demo.application.dto.ReproductionDto;
import com.example.demo.domain.entity.Reproduction;
import com.example.demo.infraestructure.mapper.ReproductionMapper;
import com.example.demo.infraestructure.persistence.ReproductionPersistence;
import com.example.demo.infraestructure.repository.ReproductionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReproductionServiceImpl implements ReproductionService {
    private final ReproductionPersistence persistence;
    private final ReproductionRepository repository;
    private final ReproductionMapper mapper;

    @Autowired
    public ReproductionServiceImpl(@Qualifier("reproductionPersistenceImpl") ReproductionPersistence persistence, ReproductionMapper mapper, ReproductionRepository repository) {
        this.persistence = persistence;
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<ReproductionDto> getAllReproductions() {
        List<Reproduction> reproductions = this.persistence.getAllReproductions();
        return mapper.toDto(reproductions);
    }

    @Override
    public Optional<ReproductionDto> getReproductionById(Long reproductionId) {
        return this.persistence.getReproductionById(reproductionId).map(mapper::toDto);
    }

    @Override
    public ReproductionDto saveReproduction(ReproductionDto reproductionDto) {
        System.out.println("Guardando canción: " + reproductionDto);
        Reproduction reproduction = this.persistence.saveReproduction(this.mapper.toEntity(reproductionDto));
        System.out.println("Canción guardada: " + reproduction);
        return this.mapper.toDto(reproduction);
    }


    @Override
    public void deleteReproduction(Long reproductionId) {
        this.persistence.deleteReproduction(reproductionId);
    }

    @Override
    public Page<ReproductionDto> getReproductionsByCriteriaStringPaged(Pageable pageable, String filter) {
        Page<Reproduction> reproductionPage = this.persistence.findAll(pageable, filter);
        return reproductionPage.map(mapper::toDto);
    }

    @Override
    public List<ReproductionDto> getReproductionsByUserName(String reproductionUserName) {
        List<Reproduction> reproductions = repository.findByUserName(reproductionUserName);
        return reproductions.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


}

