package com.example.demo.infraestructure.repository;

import com.example.demo.domain.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface SongRepository extends JpaRepository<Song, Long>, JpaSpecificationExecutor<Song> {
    //Page<User> findAll(UserSpecification specification, Pageable pageable);
}
