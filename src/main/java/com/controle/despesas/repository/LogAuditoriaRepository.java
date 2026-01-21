package com.controle.despesas.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.controle.despesas.models.LogAuditoria;

public interface LogAuditoriaRepository extends JpaRepository <LogAuditoria, UUID>{

   
    List<LogAuditoria> findByUsuarioId(UUID usuarioId);

    
    List<LogAuditoria> findByAcao(String acao);

    
    List<LogAuditoria> findByEntidade(String entidade);

    // Buscar logs em um periodo
    List<LogAuditoria> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);

    // Buscar todos ordenados por data (mais recente primeiro)
    List<LogAuditoria> findAllByOrderByDataHoraDesc();

    // Buscar logs de um usuario ordenados por data
    List<LogAuditoria> findByUsuarioIdOrderByDataHoraDesc(UUID usuarioId);


}
