package com.example.demo.infraestructure.rest;

import com.example.demo.application.dto.SongDto;
import com.example.demo.application.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

import java.util.List;
import java.util.Optional;

@RestController
public class DemoController {

    private final SongService songService;

    @Autowired
    public DemoController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping(value = "/hello")
    public Map<String, String> hello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from secure endpoint");
        return response;
    }

    @GetMapping(value = "/songs", produces = "application/json")
    public ResponseEntity<Page<SongDto>> getAllSongs(@RequestParam(value = "filter", required = false) String filter, Pageable pageable){
        Page<SongDto> songs = this.songService.getSongsByCriteriaStringPaged(pageable, filter);
        return new ResponseEntity<Page<SongDto>>(songs, HttpStatus.OK);
    }

    @PostMapping(value = "/songs", produces = "application/json", consumes = "application/json")
    ResponseEntity<SongDto> insertSong(@RequestBody SongDto songDto) {
        songDto = this.songService.saveSong(songDto);
        return new ResponseEntity<>(songDto, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/songs/{songId}")
    ResponseEntity<?> deleteSongById(@PathVariable Long songId) {
        this.songService.deleteSong(songId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/songs/{songId}")
    ResponseEntity<SongDto> getSongById(@PathVariable Long songId) {
        Optional<SongDto> song = this.songService.getSongById(songId);

        if (song.isPresent()) {
            return new ResponseEntity<>(song.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/songs/{songId}", produces = "application/json", consumes = "application/json")
    ResponseEntity<SongDto> updateSong(@PathVariable Long songId, @RequestBody SongDto songDto) {
        songDto.setId(songId);
        songDto = this.songService.saveSong(songDto);
        return new ResponseEntity<>(songDto, HttpStatus.OK);
    }

}
