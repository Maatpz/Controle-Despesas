package com.controle.despesas.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    
    private UUID id;

    @NotBlank(message = "Nome da categoria e obrigatorio")
    private String nome;

    private String descricao;

    @NotBlank(message = "Tipo e obrigatorio (RECEITA ou DESPESA)")
    private String tipo;

    private String cor;

}
