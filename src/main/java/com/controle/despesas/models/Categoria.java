package com.controle.despesas.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tb_categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    
    @NotBlank(message = "Nome da categoria é obrigatório")
    @Column(nullable = false)
    private String nome;
    
    
    private String descricao;


    @NotBlank(message = "Tipo é obrigatório")
    @Column(nullable = false)
    private String tipo;

    private String cor;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "categoria")
    private List<Transacao> transacoes = new ArrayList<>();

}
