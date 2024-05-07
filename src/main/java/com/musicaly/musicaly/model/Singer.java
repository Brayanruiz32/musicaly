package com.musicaly.musicaly.model;

import java.util.List;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Singer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; 

    private String lastname;

    private int age;
    
    @Enumerated(EnumType.STRING)
    private Genero sexo;

    @OneToMany(mappedBy = "singer")
    @Nullable
    private List<Song> songs;

    
    
    public Singer() {
    }

    public Singer(String name, String lastname, int age, Genero sexo) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.sexo = sexo;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Genero getSexo() {
        return sexo;
    }

    public void setSexo(Genero sexo) {
        this.sexo = sexo;
    }

    




}
