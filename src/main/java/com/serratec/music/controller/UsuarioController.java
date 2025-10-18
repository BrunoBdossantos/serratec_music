package com.serratec.music.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.serratec.music.domain.Usuario;
import com.serratec.music.repository.UsuarioRepository;
import com.serratec.music.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Operation(summary = "Lista todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados e seus perfis.")
	@GetMapping
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@Operation(summary = "Busca um usuário por ID", description = "Retorna um usuário específico (e seu perfil) baseado no seu ID.")
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + id));
		return ResponseEntity.ok(usuario);
	}

	@Operation(summary = "Cria um novo usuário e seu perfil", description = "Cadastra um novo usuário e seu perfil aninhado em uma única requisição.")
	@PostMapping
	public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario) {

		Usuario novoUsuario = usuarioRepository.save(usuario);
		return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
	}

	@Operation(summary = "Atualiza um usuário existente", description = "Modifica os dados de um usuário e seu perfil com base no seu ID.")
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuarioDetails) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + id));

		usuario.setNome(usuarioDetails.getNome());
		usuario.setEmail(usuarioDetails.getEmail());
		if (usuarioDetails.getPerfil() != null) {
			usuario.getPerfil().setTelefone(usuarioDetails.getPerfil().getTelefone());
			usuario.getPerfil().setDataNascimento(usuarioDetails.getPerfil().getDataNascimento());
		}

		final Usuario updatedUsuario = usuarioRepository.save(usuario);
		return ResponseEntity.ok(updatedUsuario);
	}

	@Operation(summary = "Deleta um usuário", description = "Remove um usuário e seu perfil associado do sistema (Cascade) com base no seu ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + id));
		usuarioRepository.delete(usuario);
		return ResponseEntity.noContent().build();
	}
}
