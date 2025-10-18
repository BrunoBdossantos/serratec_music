package com.serratec.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serratec.music.domain.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {}
