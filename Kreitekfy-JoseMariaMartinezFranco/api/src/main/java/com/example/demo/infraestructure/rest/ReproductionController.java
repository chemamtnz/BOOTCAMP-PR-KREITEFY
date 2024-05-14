package com.example.demo.infraestructure.rest;

import com.example.demo.application.dto.ReproductionDto;
import com.example.demo.domain.entity.Reproduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.application.service.ReproductionService;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@RestController
public class ReproductionController {

    private final ReproductionService reproductionService;

    @Autowired
    public ReproductionController(ReproductionService reproductionService) {
        this.reproductionService = reproductionService;
    }

    @GetMapping(value = "/reproductions", produces = "application/json")
    public ResponseEntity<Page<ReproductionDto>> getAllReproductions(@RequestParam(value = "filter", required = false) String filter, Pageable pageable){
        Page<ReproductionDto> reproductions = this.reproductionService.getReproductionsByCriteriaStringPaged(pageable, filter);
        return new ResponseEntity<Page<ReproductionDto>>(reproductions, HttpStatus.OK);
    }

    @PostMapping(value = "/reproductions", produces = "application/json", consumes = "application/json")
    ResponseEntity<ReproductionDto> insertReproduction(@RequestBody ReproductionDto reproductionDto) {
        reproductionDto = this.reproductionService.saveReproduction(reproductionDto);
        return new ResponseEntity<>(reproductionDto, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/reproductions/{reproductionId}")
    ResponseEntity<?> deleteReproductionById(@PathVariable Long reproductionId) {
        this.reproductionService.deleteReproduction(reproductionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/reproductions/{reproductionId}")
    ResponseEntity<ReproductionDto> getReproductionById(@PathVariable Long reproductionId) {
        Optional<ReproductionDto> reproduction = this.reproductionService.getReproductionById(reproductionId);

        if (reproduction.isPresent()) {
            return new ResponseEntity<>(reproduction.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/reproductions/{reproductionId}", produces = "application/json", consumes = "application/json")
    ResponseEntity<ReproductionDto> updateReproduction(@PathVariable Long reproductionId, @RequestBody ReproductionDto reproductionDto) {
        reproductionDto.setId(reproductionId);
        reproductionDto = this.reproductionService.saveReproduction(reproductionDto);
        return new ResponseEntity<>(reproductionDto, HttpStatus.OK);
    }

    @GetMapping(value = "/reproductions/userName")
    public ResponseEntity<List<ReproductionDto>> getReproductionsByUserName(@RequestParam String userName) {
        List<ReproductionDto> reproductions = this.reproductionService.getReproductionsByUserName(userName);

        if (!reproductions.isEmpty()) {
            return new ResponseEntity<>(reproductions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}

