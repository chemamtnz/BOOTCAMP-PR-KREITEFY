package com.example.demo.infraestructure.persistenceImpl;

import com.example.demo.application.service.SongService;
import com.example.demo.application.dto.SongDto;
import com.example.demo.domain.entity.Song;
import com.example.demo.infraestructure.mapper.SongMapper;
import com.example.demo.infraestructure.persistence.SongPersistence;

import com.example.demo.infraestructure.repository.SongRepository;
import com.example.demo.infraestructure.specs.SongSpecification;
import com.example.demo.infraestructure.specs.shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public class SongPersistenceImpl implements SongPersistence {
    private final SongRepository songRepository;

    @Autowired
    public SongPersistenceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> getAllSongs() {
        return this.songRepository.findAll();
    }

    @Override
    public Optional<Song> getSongById(Long songId) {
        return this.songRepository.findById(songId);
    }

    @Override
    public Song saveSong(Song song) {
        return this.songRepository.save(song);
    }

    @Override
    public void deleteSong(Long songId) {
        this.songRepository.deleteById(songId);
    }

    @Override
    public Page<Song> findAll(Pageable pageable, String filters) {
        SongSpecification specification = new SongSpecification(SearchCriteriaHelper.fromFilterString(filters));
        return this.songRepository.findAll(specification, pageable);
    }
}
