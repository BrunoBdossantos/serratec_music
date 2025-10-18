package com.serratec.music.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public class PlaylistRequestDTO {
    
	@Schema(description = "Nome da playlist", example = "Rock Clássico")
	@NotBlank
    private String nome;
	
	@Schema(description = "Breve descrição da playlist", example = "O melhor do rock dos anos 80 e 90")
    private String descricao;
	
	@Schema(description = "ID do usuário que é o dono da playlist", example = "1")
    @NotNull
    private Long donoId; 

   
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Long getDonoId() { return donoId; }
    public void setDonoId(Long donoId) { this.donoId = donoId; }
}