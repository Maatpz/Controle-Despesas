package com.controle.despesas.dto.request;

import com.controle.despesas.models.enums.Tipo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequest {

    @NotNull(message = "Valor e obrigatorio")
    @Positive(message = "Valor deve ser positivo")
    private Double valor;

    @Size(max = 255, message = "Descricao deve ter no maximo 255 caracteres")
    private String descricao;

    @NotNull(message = "Tipo e obrigatorio")
    private Tipo tipo;
}
