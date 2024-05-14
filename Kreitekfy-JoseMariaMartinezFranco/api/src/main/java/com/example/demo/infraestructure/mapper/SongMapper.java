package com.example.demo.infraestructure.mapper;

import com.example.demo.application.dto.SongDto;
import com.example.demo.domain.entity.Song;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper extends EntityMapper<SongDto, Song> {
    default Song fromId(Long id) {

        if (id == null) return null;

        Song song = new Song();
        song.setId(id);
        return song;
    }
}
