package com.controle.despesas.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "log_auditoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogAuditoria {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    
    @Column(nullable = false)
    private String acao;

    private String entidade;

 
    @Column(name = "entidade_id")
    private UUID entidadeId;

    
    @Column(length = 1000)
    private String descricao;


    @Column(name = "usuario_email")
    private String usuarioEmail;

    
    @Column(name = "usuario_id")
    private Long usuarioId;

   
    @Column(name = "ip_address")
    private String ipAddress;

   
    @Column(name = "data_hora", nullable = false)
    @UpdateTimestamp
    private LocalDateTime dataHora;

   
}
