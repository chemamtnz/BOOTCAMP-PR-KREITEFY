package com.example.demo.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "reproductions")
public class Reproduction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSequence")
    private Long id;
    @Column(nullable = false, length = 100)
    @Size(min = 3, max = 100)
    private String userName;
    @Column(nullable = false, length = 100)
    @Size(min = 3, max = 100)
    private String song;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Reproduction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String user) {
        this.userName = user;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

