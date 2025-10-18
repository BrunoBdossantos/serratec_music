package com.serratec.music.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.serratec.music.domain.Musica;
import com.serratec.music.repository.MusicaRepository;
import com.serratec.music.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

	@Autowired
	private MusicaRepository musicaRepository;

	@Operation(summary = "Lista todas as músicas", description = "Retorna uma lista com todas as músicas cadastradas.")
	@GetMapping
	public List<Musica> getAllMusicas() {
		return musicaRepository.findAll();
	}

	@Operation(summary = "Busca uma música por ID", description = "Retorna uma música específica baseada no seu ID.")
	@GetMapping("/{id}")
	public ResponseEntity<Musica> getMusicaById(@PathVariable Long id) {
		Musica musica = musicaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Música não encontrada com o id: " + id));
		return ResponseEntity.ok(musica);
	}

	@Operation(summary = "Cria uma nova música", description = "Cadastra uma nova música no sistema, podendo associar artistas existentes.")
	@PostMapping
	public ResponseEntity<Musica> createMusica(@Valid @RequestBody Musica musica) {
		return new ResponseEntity<>(musicaRepository.save(musica), HttpStatus.CREATED);
	}

	@Operation(summary = "Atualiza uma música existente", description = "Modifica os dados de uma música (incluindo sua lista de artistas) com base no seu ID.")
	@PutMapping("/{id}")
	public ResponseEntity<Musica> updateMusica(@PathVariable Long id, @Valid @RequestBody Musica musicaDetails) {
		Musica musica = musicaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Música não encontrada com o id: " + id));

		musica.setTitulo(musicaDetails.getTitulo());
		musica.setGenero(musicaDetails.getGenero());
		musica.setMinutos(musicaDetails.getMinutos());
		musica.setArtistas(musicaDetails.getArtistas());

		final Musica updatedMusica = musicaRepository.save(musica);
		return ResponseEntity.ok(updatedMusica);
	}

	@Operation(summary = "Deleta uma música", description = "Remove uma música do sistema com base no seu ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMusica(@PathVariable Long id) {
		Musica musica = musicaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Música não encontrada com o id: " + id));
		musicaRepository.delete(musica);
		return ResponseEntity.noContent().build();
	}
}
