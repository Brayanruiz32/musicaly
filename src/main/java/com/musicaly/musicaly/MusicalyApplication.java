package com.musicaly.musicaly;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import com.musicaly.musicaly.principal.Principal;
import com.musicaly.musicaly.repositories.SingerRepository;
import com.musicaly.musicaly.repositories.SongRepository;

@SpringBootApplication
public class MusicalyApplication implements CommandLineRunner {
	@Autowired
	SingerRepository singerRepository;
	@Autowired
	SongRepository songRepository;
	public static void main(String[] args) {
		SpringApplication.run(MusicalyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<JpaRepository> repositorios = Arrays.asList(singerRepository, songRepository);
		Principal miPrincipal = new Principal(repositorios);
		miPrincipal.lanzarMenu();
	}
}
