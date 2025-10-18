package com.serratec.music.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


@Entity
public class Perfil {

	@Schema(description = "ID único do perfil (Gerado automaticamente)") 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Long id;

	@Schema(description = "Número de telefone do usuário", example = "21987654321")
	@Size(max = 20)
    private String telefone;

	@Schema(description = "Data de nascimento do usuário", example = "1990-12-31") 
	@Past 
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
}
