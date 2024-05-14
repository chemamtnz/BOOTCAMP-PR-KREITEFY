package com.example.demo.application.dto;

public class SongDto {
    private Long id;
    private String image;
    private String style;
    private String artist;
    private String album;
    private String name;
    private Double duration;
    private Double reproductions;
    private Double valoration;

    public SongDto() {
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
