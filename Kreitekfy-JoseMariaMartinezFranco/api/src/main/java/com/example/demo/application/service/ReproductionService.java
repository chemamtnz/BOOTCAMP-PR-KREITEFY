package com.example.demo.application.service;

import com.example.demo.application.dto.ReproductionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReproductionService {
    List<ReproductionDto> getAllReproductions();
    Optional<ReproductionDto> getReproductionById(Long reproductionId);
    List<ReproductionDto> getReproductionsByUserName(String reproductionUserName);
    ReproductionDto saveReproduction(ReproductionDto reproduction);
    void deleteReproduction(Long reproductionId);
    Page<ReproductionDto> getReproductionsByCriteriaStringPaged(Pageable pageable, String filter);
}
