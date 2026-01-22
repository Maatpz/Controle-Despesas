package com.controle.despesas.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
