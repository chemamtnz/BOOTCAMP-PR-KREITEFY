package com.example.demo.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;
@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSequence")
    private Long id;

    @Column(nullable = false, length = 100)
    @Size(min = 3, max = 100)
    private String style;
    @Column(nullable = false, length = 100)
    @Size(min = 3, max = 100)
    private String artist;
    @Column(nullable = false, length = 100)
    @Size(min = 3, max = 100)
    private String album;
    @Column(nullable = false, length = 100)
    @Size(min = 3, max = 100)
    private String name;
    @Column(nullable = false)
    @Positive
    private Double duration;
    @Column(nullable = false)
    @Positive
    private Double reproductions;
    
    @Positive
    private Double valoration;

    private String image;

    public Song() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Double getReproductions() {
        return reproductions;
    }

    public void setReproductions(Double reproductions) {
        this.reproductions = reproductions;
    }

    public Double getValoration() {
        return valoration;
    }

    public void setValoration(Double valoration) {
        this.valoration = valoration;
    }
}
