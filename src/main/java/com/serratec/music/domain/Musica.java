package com.serratec.music.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import com.serratec.music.domain.enums.GeneroMusical;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Musica {

	@Schema(description = "ID único da música (Gerado automaticamente)") 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_musica")
    private Long id;

	@Schema(description = "Título da música", example = "Tempo Perdido")
	@NotBlank
    private String titulo;

	@Schema(description = "Duração da música em minutos", example = "4")
	@Positive
    private Integer minutos;

	@Schema(description = "Gênero principal da música", example = "ROCK")
	@Enumerated(EnumType.STRING)
    private GeneroMusical genero;

    // Relacionamento
	
	@Schema(description = "Lista de artistas que participam da música (incluindo feats)")
	@ManyToMany
    @JoinTable(
        name = "musica_artista",
        joinColumns = @JoinColumn(name = "id_musica_fk"),
        inverseJoinColumns = @JoinColumn(name = "id_artista_fk")
    )
    private Set<Artista> artistas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public Integer getMinutos() { return minutos; }
    public void setMinutos(Integer minutos) { this.minutos = minutos; }
    public GeneroMusical getGenero() { return genero; }
    public void setGenero(GeneroMusical genero) { this.genero = genero; }
    public Set<Artista> getArtistas() { return artistas; }
    public void setArtistas(Set<Artista> artistas) { this.artistas = artistas; }
}
