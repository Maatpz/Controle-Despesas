package com.controle.despesas.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.controle.despesas.models.Transacao;
import com.controle.despesas.models.enums.Tipo;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByUsuarioId(Long usuarioId);

    List<Transacao> findByUsuarioIdAndTipo(Long usuarioId, Tipo tipo);

    List<Transacao> findByUsuarioIdAndDataBetween(Long usuarioId, LocalDateTime dataInicio, LocalDateTime dataFim);

    @Query("SELECT SUM(t.valor) FROM Transacao t WHERE t.usuario.id = :usuarioId AND t.tipo = :tipo")
    Double somarValorPorUsuarioETipo(@Param("usuarioId") Long usuarioId, @Param("tipo") Tipo tipo);

    @Query("SELECT SUM(t.valor) FROM Transacao t WHERE t.usuario.id = :usuarioId AND t.tipo = :tipo AND t.data BETWEEN :dataInicio AND :dataFim")
    Double somarValorPorUsuarioTipoEPeriodo(@Param("usuarioId") Long usuarioId, 
                                            @Param("tipo") Tipo tipo, 
                                            @Param("dataInicio") LocalDateTime dataInicio, 
                                            @Param("dataFim") LocalDateTime dataFim);
}

  

    
