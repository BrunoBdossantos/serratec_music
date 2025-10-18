package com.serratec.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serratec.music.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}