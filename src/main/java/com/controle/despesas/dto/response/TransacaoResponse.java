package com.controle.despesas.dto.response;

import java.time.LocalDateTime;
import com.controle.despesas.models.enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoResponse {

    private Long id;
    private Double valor;
    private String descricao;
    private Tipo tipo;
    private LocalDateTime data;
    private Long usuarioId;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
