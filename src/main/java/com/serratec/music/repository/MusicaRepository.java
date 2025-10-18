package com.serratec.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serratec.music.domain.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Long> {}
