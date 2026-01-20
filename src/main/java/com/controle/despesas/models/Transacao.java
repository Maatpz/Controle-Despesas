package com.controle.despesas.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_transacoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Valor e obrigatorio")
    @Positive(message = "Valor deve ser positivo")
    @Column(nullable = false)
    private Double valor;

    private String descricao;

    
    @NotNull(message = "Tipo e obrigatorio")
    @Column(nullable = false)
    private String tipo;

    
    @Column(nullable = false, name = "data_transaçao")
    @CreationTimestamp
    private LocalDate data;


    @Column(name = "mes_ano")
    private String mesAno;

    // Relacionamento: uma transacao pertence a um usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Relacionamento: uma transacao pertence a uma categoria
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;


    @Column(name = "data_criacao")
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    
    @Column(name = "data_atualizacao")
    @CreationTimestamp
    private LocalDateTime data_atualizacao;


}
