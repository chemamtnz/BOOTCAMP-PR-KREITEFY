package com.example.demo.infraestructure.persistenceImpl;

import com.example.demo.domain.entity.Reproduction;
import com.example.demo.infraestructure.persistence.ReproductionPersistence;
import com.example.demo.infraestructure.repository.ReproductionRepository;
import com.example.demo.infraestructure.specs.ReproductionSpecification;
import com.example.demo.infraestructure.specs.shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public class ReproductionPersistenceImpl implements ReproductionPersistence {
    private final ReproductionRepository reproductionRepository;

    @Autowired
    public ReproductionPersistenceImpl(ReproductionRepository reproductionRepository) {
        this.reproductionRepository = reproductionRepository;
    }

    @Override
    public List<Reproduction> getAllReproductions() {
        return this.reproductionRepository.findAll();
    }

    @Override
    public Optional<Reproduction> getReproductionById(Long reproductionId) {
        return this.reproductionRepository.findById(reproductionId);
    }

    @Override
    public Reproduction saveReproduction(Reproduction reproduction) {
        return this.reproductionRepository.save(reproduction);
    }

    @Override
    public void deleteReproduction(Long reproductionId) {
        this.reproductionRepository.deleteById(reproductionId);
    }

    @Override
    public Page<Reproduction> findAll(Pageable pageable, String filters) {
        ReproductionSpecification specification = new ReproductionSpecification(SearchCriteriaHelper.fromFilterString(filters));
        return this.reproductionRepository.findAll(specification, pageable);
    }

    @Override
    public List<Reproduction> getReproductionsByUserName(String userName) {
        return reproductionRepository.findByUserName(userName);
    }

}

