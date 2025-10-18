package com.serratec.music.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario {

	@Schema(description = "ID único do usuário (Gerado automaticamente)") 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

	@Schema(description = "Nome completo do usuário", example = "João da Silva") 
	@NotBlank(message = "O nome não pode ser vazio.")
    @Size(max = 80, message = "O nome deve ter no máximo 80 caracteres.")
    private String nome;

	@Schema(description = "E-mail único do usuário para login", example = "joao.silva@email.com") 
	@NotBlank(message = "O e-mail não pode ser vazio.")
    @Email(message = "Formato de e-mail inválido.")
    @Column(unique = true)
    private String email;

    // relacionamento

    
	@Schema(description = "Perfil de informações adicionais do usuário") 
	@OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "id_perfil_fk", referencedColumnName = "id_perfil")
    @Valid 
    private Perfil perfil;

    
	@Schema(description = "Lista de playlists criadas por este usuário") 
	@OneToMany(mappedBy = "dono")
    @JsonManagedReference 
    private Set<Playlist> playlists;


    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }
    public Set<Playlist> getPlaylists() { return playlists; }
    public void setPlaylists(Set<Playlist> playlists) { this.playlists = playlists; }
}