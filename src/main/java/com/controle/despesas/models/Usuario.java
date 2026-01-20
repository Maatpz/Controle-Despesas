package com.controle.despesas.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tb_usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario{

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Size(max = 50)
    private String nome;

    @NotBlank(message = "Email e obrigatorio")
    @Email(message = "Email invalido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Senha e obrigatoria")
    @Column(nullable = false)
    private String senha;

    // ROLE_USER ou ROLE_ADMIN
    @Column(nullable = false)
    private String role = "ROLE_USER";

    // @Enumerated(EnumType.STRING)
    // private Role roleEnum = Role.USER;

    private Boolean ativo = true;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Transacao> transacoes = new ArrayList<>();


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Categoria> categorias = new ArrayList<>();


    @Column(name = "saldo_economia")
    private Double saldoEconomia = 0.0;

    @Column(name = "data_criacao")
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    @UpdateTimestamp
    private LocalDateTime data_atualizacao;
}
