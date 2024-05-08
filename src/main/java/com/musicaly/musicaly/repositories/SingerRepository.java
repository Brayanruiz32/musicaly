package com.musicaly.musicaly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.musicaly.musicaly.model.Singer;
import com.musicaly.musicaly.model.Song;

public interface SingerRepository extends JpaRepository<Singer, Long> {




    @Query("SELECT song FROM Singer s JOIN s.songs song WHERE s.name LIKE %:cantante%")
    List<Song> obtenerCanciones(String cantante);




}
