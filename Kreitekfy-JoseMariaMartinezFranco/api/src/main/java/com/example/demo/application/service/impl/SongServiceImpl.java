package com.example.demo.application.service.impl;

import com.example.demo.application.service.SongService;
import com.example.demo.application.dto.SongDto;
import com.example.demo.domain.entity.Song;
import com.example.demo.infraestructure.mapper.SongMapper;
import com.example.demo.infraestructure.persistence.SongPersistence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private final SongPersistence persistence;
    private final SongMapper mapper;

    @Autowired
    public SongServiceImpl(@Qualifier("songPersistenceImpl") SongPersistence persistence, SongMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public List<SongDto> getAllSongs() {
        List<Song> songs = this.persistence.getAllSongs();
        return mapper.toDto(songs);
    }

    @Override
    public Optional<SongDto> getSongById(Long songId) {
        return this.persistence.getSongById(songId).map(mapper::toDto);
    }

    @Override
    public SongDto saveSong(SongDto songDTO) {
        System.out.println("Guardando canción: " + songDTO);
        Song song = this.persistence.saveSong(this.mapper.toEntity(songDTO));
        System.out.println("Canción guardada: " + song);
        return this.mapper.toDto(song);
    }


    @Override
    public void deleteSong(Long songId) {
        this.persistence.deleteSong(songId);
    }

    @Override
    public Page<SongDto> getSongsByCriteriaStringPaged(Pageable pageable, String filter) {
        Page<Song> songPage = this.persistence.findAll(pageable, filter);
        return songPage.map(mapper::toDto);
    }
}
