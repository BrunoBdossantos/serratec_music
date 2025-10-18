package com.serratec.music.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Artista {

	@Schema(description = "ID único do artista (Gerado automaticamente)") 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artista")
    private Long id;

	@Schema(description = "Nome do artista ou banda", example = "Legião Urbana")
	@NotBlank(message = "Nome do artista é obrigatório.")
    private String nome;

	@Schema(description = "Nacionalidade do artista (país de origem)", example = "Brasileira")
	private String nacionalidade;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }
}
