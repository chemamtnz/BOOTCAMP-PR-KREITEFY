package com.example.demo.application.service;

import com.example.demo.application.dto.SongDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
public interface SongService {
    List<SongDto> getAllSongs();
    Optional<SongDto> getSongById(Long songId);
    SongDto saveSong(SongDto song);
    void deleteSong(Long songId);
    Page<SongDto> getSongsByCriteriaStringPaged(Pageable pageable, String filter);
}
