package com.serratec.music.controller;

import com.serratec.music.domain.Musica;
import com.serratec.music.domain.Playlist;
import com.serratec.music.domain.Usuario;
import com.serratec.music.dto.PlaylistRequestDTO;
import com.serratec.music.dto.PlaylistUpdateMusicasDTO;
import com.serratec.music.exception.ResourceNotFoundException;
import com.serratec.music.repository.MusicaRepository;
import com.serratec.music.repository.PlaylistRepository;
import com.serratec.music.repository.UsuarioRepository;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired private PlaylistRepository playlistRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private MusicaRepository musicaRepository;

    @Operation(summary = "Lista todas as playlists", description = "Retorna uma lista com todas as playlists cadastradas.")
    @GetMapping
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    @Operation(summary = "Busca uma playlist por ID", description = "Retorna uma playlist específica (com suas músicas) baseada no seu ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Long id) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist não encontrada com o id: " + id));
        return ResponseEntity.ok(playlist);
    }

    
    @Operation(summary = "Cria uma nova playlist", description = "Cadastra uma nova playlist associando-a a um usuário existente pelo 'donoId'.")
    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@Valid @RequestBody PlaylistRequestDTO dto) {
        
        Usuario dono = usuarioRepository.findById(dto.getDonoId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + dto.getDonoId()));

        Playlist novaPlaylist = new Playlist();
        novaPlaylist.setNome(dto.getNome());
        novaPlaylist.setDescricao(dto.getDescricao());
        novaPlaylist.setDono(dono); // 3. Associar o dono

        // 4. Salvar no banco
        Playlist playlistSalva = playlistRepository.save(novaPlaylist);
        return new ResponseEntity<>(playlistSalva, HttpStatus.CREATED);
    }

   
    @Operation(summary = "Atualiza as músicas de uma playlist", description = "Substitui a lista de músicas de uma playlist existente. Envie uma lista de IDs de músicas.")
    @PutMapping("/{id}/musicas") 
    public ResponseEntity<Playlist> updateMusicasDaPlaylist(@PathVariable Long id, @Valid @RequestBody PlaylistUpdateMusicasDTO dto) {
        
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist não encontrada com o id: " + id));

        List<Musica> musicasEncontradas = musicaRepository.findAllById(dto.getMusicaIds());

        Set<Musica> musicas = new HashSet<>(musicasEncontradas);

        playlist.setMusicas(musicas);

        
        Playlist playlistAtualizada = playlistRepository.save(playlist);
        return ResponseEntity.ok(playlistAtualizada);
    }

    @Operation(summary = "Deleta uma playlist", description = "Remove uma playlist do sistema com base no seu ID (não apaga as músicas, apenas a playlist).")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist não encontrada com o id: " + id));
        playlistRepository.delete(playlist);
        return ResponseEntity.noContent().build();
    }
}