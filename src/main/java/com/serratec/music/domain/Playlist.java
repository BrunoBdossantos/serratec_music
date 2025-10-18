package com.serratec.music.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Playlist {

	@Schema(description = "ID único da playlist (Gerado automaticamente)")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_playlist")
    private Long id;

	@Schema(description = "Nome da playlist", example = "Meu Rock BR")
	@NotBlank
    private String nome;

	@Schema(description = "Breve descrição da playlist", example = "O melhor do rock nacional")
	@Size(max = 255)
    private String descricao;

    // relacionamentos

	@Schema(description = "Usuário que criou (dono) da playlist")
	@ManyToOne
    @JoinColumn(name = "id_usuario_fk")
    @JsonBackReference // Evita loop infinito no JSON
    private Usuario dono;

	@Schema(description = "Lista de músicas contidas nesta playlist") 
	@ManyToMany
    @JoinTable(
        name = "playlist_musica",
        joinColumns = @JoinColumn(name = "id_playlist_fk"),
        inverseJoinColumns = @JoinColumn(name = "id_musica_fk")
    )
    private Set<Musica> musicas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Usuario getDono() { return dono; }
    public void setDono(Usuario dono) { this.dono = dono; }
    public Set<Musica> getMusicas() { return musicas; }
    public void setMusicas(Set<Musica> musicas) { this.musicas = musicas; }
}