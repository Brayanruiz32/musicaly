package com.musicaly.musicaly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicaly.musicaly.model.Song;

public interface SongRepository extends  JpaRepository<Song, Long>{

}
