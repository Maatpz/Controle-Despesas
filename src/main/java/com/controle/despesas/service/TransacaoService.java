package com.controle.despesas.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.controle.despesas.dto.request.TransacaoRequest;
import com.controle.despesas.dto.response.ResumoFinanceiroResponse;
import com.controle.despesas.dto.response.TransacaoResponse;
import com.controle.despesas.models.Transacao;
import com.controle.despesas.models.Usuario;
import com.controle.despesas.models.enums.Tipo;
import com.controle.despesas.repository.TransacaoRepository;
import com.controle.despesas.repository.UsuariorRepository;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private UsuariorRepository usuarioRepository;

    @Transactional
    public TransacaoResponse criaTransacao(Long usuarioId, TransacaoRequest request) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        Transacao transacao = new Transacao();
        transacao.setValor(request.getValor());
        transacao.setDescricao(request.getDescricao());
        transacao.setTipo(request.getTipo());
        transacao.setUsuario(usuario);
        transacao.setData(LocalDateTime.now());

        transacao = transacaoRepository.save(transacao);
        return toResponse(transacao);
    }

    public TransacaoResponse buscarPorId(Long id) {
        Transacao transacao = transacaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Transacao nao encontrada"));
        return toResponse(transacao);
    }

    public List<TransacaoResponse> listarPorUsuario(Long usuarioId) {
    List<Transacao> transacoes = transacaoRepository.findByUsuarioId(usuarioId);
    List<TransacaoResponse> responses = new ArrayList<>();
    for (Transacao transacao : transacoes) {
        responses.add(toResponse(transacao));
    }
    return responses;

        // return transacaoRepository.findByUsuarioId(usuarioId).stream()
        //     .map(transacao -> toResponse(transacao))
        //     // .map(this::toResponse)
        //     .collect(Collectors.toList());
    }


    public List<TransacaoResponse> listarPorUsuarioETipo(Long usuarioId, Tipo tipo) {
        return transacaoRepository.findByUsuarioIdAndTipo(usuarioId, tipo).stream()
            .map(transacao -> toResponse(transacao))
            // .map(this::toResponse)
            .collect(Collectors.toList());
    }

    public List<TransacaoResponse> listarPorUsuarioEPeriodo(Long usuarioId, LocalDateTime dataInicio, LocalDateTime dataFim) {
        return transacaoRepository.findByUsuarioIdAndDataBetween(usuarioId, dataInicio, dataFim).stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    @Transactional
    public TransacaoResponse atualizar(Long id, TransacaoRequest request) {
        Transacao transacao = transacaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Transacao nao encontrada"));

        transacao.setValor(request.getValor());
        transacao.setDescricao(request.getDescricao());
        transacao.setTipo(request.getTipo());

        transacao = transacaoRepository.save(transacao);
        return toResponse(transacao);
    }

    @Transactional
    public void deletar(Long id) {
        if (!transacaoRepository.existsById(id)) {
            throw new RuntimeException("Transacao nao encontrada");
        }
        transacaoRepository.deleteById(id);
    }

    public ResumoFinanceiroResponse obterResumoFinanceiro(Long usuarioId) {
        Double totalReceitas = transacaoRepository.somarValorPorUsuarioETipo(usuarioId, Tipo.RECEITA);
        Double totalDespesas = transacaoRepository.somarValorPorUsuarioETipo(usuarioId, Tipo.DESPESA);
        return new ResumoFinanceiroResponse(totalReceitas, totalDespesas);
    }

    public ResumoFinanceiroResponse obterResumoFinanceiroPorPeriodo(Long usuarioId, LocalDateTime dataInicio, LocalDateTime dataFim) {
        Double totalReceitas = transacaoRepository.somarValorPorUsuarioTipoEPeriodo(usuarioId, Tipo.RECEITA, dataInicio, dataFim);
        Double totalDespesas = transacaoRepository.somarValorPorUsuarioTipoEPeriodo(usuarioId, Tipo.DESPESA, dataInicio, dataFim);
        return new ResumoFinanceiroResponse(totalReceitas, totalDespesas);
    }

    private TransacaoResponse toResponse(Transacao transacao) {
        TransacaoResponse response = new TransacaoResponse();
        response.setId(transacao.getId());
        response.setValor(transacao.getValor());
        response.setDescricao(transacao.getDescricao());
        response.setTipo(transacao.getTipo());
        response.setData(transacao.getData());
        response.setUsuarioId(transacao.getUsuario().getId());
        response.setDataCriacao(transacao.getDataCriacao());
        response.setDataAtualizacao(transacao.getDataAtualizacao());
        return response;
    }
}
