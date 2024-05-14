package com.example.demo.infraestructure.persistence;

import com.example.demo.application.dto.SongDto;
import com.example.demo.domain.entity.Song;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongPersistence {
    List<Song> getAllSongs();
    Optional<Song> getSongById(Long songId);
    Song saveSong(Song song);
    void deleteSong(Long songId);
    Page<Song> findAll(Pageable pageable, String filter);
}
