package com.controle.despesas.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.controle.despesas.models.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao,UUID>{

    List<Transacao> findByUsuarioId(UUID usuarioId);


    List<Transacao> findByUsuarioIdAndMesAno(UUID usuarioId, String mesAno);

   
    List<Transacao> findByUsuarioIdAndTipo(UUID usuarioId, String tipo);

    
    List<Transacao> findByUsuarioIdAndCategoriaId(UUID usuarioId, Long categoriaId);

    
    List<Transacao> findByUsuarioIdAndMesAnoAndTipo(UUID usuarioId, String mesAno, String tipo);

    // Somar total de receitas de um mes para um usuario
    @Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transacao t " +
           "WHERE t.usuario.id = :usuarioId AND t.mesAno = :mesAno AND t.tipo = 'RECEITA'")
    Double somarReceitasPorMes(@Param("usuarioId") UUID usuarioId, @Param("mesAno") String mesAno);

    // Somar total de despesas de um mes para um usuario
    @Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transacao t " +
           "WHERE t.usuario.id = :usuarioId AND t.mesAno = :mesAno AND t.tipo = 'DESPESA'")
    Double somarDespesasPorMes(@Param("usuarioId") UUID usuarioId, @Param("mesAno") String mesAno);

    // Listar meses disponiveis para um usuario (para mostrar no filtro)
    @Query("SELECT DISTINCT t.mesAno FROM Transacao t WHERE t.usuario.id = :usuarioId ORDER BY t.mesAno DESC")
    List<String> listarMesesDisponiveis(@Param("usuarioId") UUID usuarioId);

    // Somar gastos por categoria em um mes (para o filtro de onde gasta mais)
    @Query("SELECT t.categoria.nome, SUM(t.valor) FROM Transacao t " +
           "WHERE t.usuario.id = :usuarioId AND t.mesAno = :mesAno AND t.tipo = 'DESPESA' " +
           "GROUP BY t.categoria.nome ORDER BY SUM(t.valor) DESC")
    List<Object[]> somarGastosPorCategoria(@Param("usuarioId") UUID usuarioId, @Param("mesAno") String mesAno);
}

    
