package com.serratec.music.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.serratec.music.domain.Artista;
import com.serratec.music.repository.ArtistaRepository;
import com.serratec.music.exception.ResourceNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

	@Autowired
	private ArtistaRepository artistaRepository;

	@Operation(summary = "Lista todos os artistas", description = "Retorna uma lista com todos os artistas cadastrados.")
	@GetMapping
	public List<Artista> getAllArtistas() {
		return artistaRepository.findAll();
	}

	@Operation(summary = "Busca um artista por ID", description = "Retorna um artista específico baseado no seu ID.")
	@GetMapping("/{id}")
	public ResponseEntity<Artista> getArtistaById(@PathVariable Long id) {
		Artista artista = artistaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Artista não encontrado com o id: " + id));
		return ResponseEntity.ok(artista);
	}

	@Operation(summary = "Cria um novo artista", description = "Cadastra um novo artista no sistema.")
	@PostMapping
	public ResponseEntity<Artista> createArtista(@Valid @RequestBody Artista artista) {
		Artista novoArtista = artistaRepository.save(artista);
		return new ResponseEntity<>(novoArtista, HttpStatus.CREATED);
	}

	@Operation(summary = "Atualiza um artista existente", description = "Modifica os dados de um artista com base no seu ID.")
	@PutMapping("/{id}")
	public ResponseEntity<Artista> updateArtista(@PathVariable Long id, @Valid @RequestBody Artista artistaDetails) {
		Artista artista = artistaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Artista não encontrado com o id: " + id));

		artista.setNome(artistaDetails.getNome());
		artista.setNacionalidade(artistaDetails.getNacionalidade());
		final Artista updatedArtista = artistaRepository.save(artista);
		return ResponseEntity.ok(updatedArtista);
	}

	@Operation(summary = "Deleta um artista", description = "Remove um artista do sistema com base no seu ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteArtista(@PathVariable Long id) {
		Artista artista = artistaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Artista não encontrado com o id: " + id));
		artistaRepository.delete(artista);
		return ResponseEntity.noContent().build();
	}
}
