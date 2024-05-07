package com.musicaly.musicaly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicaly.musicaly.model.Singer;

public interface SingerRepository extends JpaRepository<Singer, Long> {

}
