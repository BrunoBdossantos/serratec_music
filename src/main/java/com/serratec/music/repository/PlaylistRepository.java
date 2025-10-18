package com.serratec.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serratec.music.domain.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {}