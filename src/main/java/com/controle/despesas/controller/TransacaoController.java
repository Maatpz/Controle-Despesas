package com.controle.despesas.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controle.despesas.dto.request.TransacaoRequest;
import com.controle.despesas.dto.response.ResumoFinanceiroResponse;
import com.controle.despesas.dto.response.TransacaoResponse;
import com.controle.despesas.models.enums.Tipo;
import com.controle.despesas.service.TransacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<TransacaoResponse> criar(@PathVariable Long usuarioId,
                                                    @Valid @RequestBody TransacaoRequest request) {
        TransacaoResponse response = transacaoService.criaTransacao(usuarioId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponse> buscarPorId(@PathVariable Long id) {
        TransacaoResponse response = transacaoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TransacaoResponse>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<TransacaoResponse> response = transacaoService.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{usuarioId}/tipo/{tipo}")
    public ResponseEntity<List<TransacaoResponse>> listarPorUsuarioETipo(
            @PathVariable Long usuarioId,
            @PathVariable Tipo tipo) {
        List<TransacaoResponse> response = transacaoService.listarPorUsuarioETipo(usuarioId, tipo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{usuarioId}/periodo")
    public ResponseEntity<List<TransacaoResponse>> listarPorUsuarioEPeriodo(
            @PathVariable Long usuarioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        List<TransacaoResponse> response = transacaoService.listarPorUsuarioEPeriodo(usuarioId, dataInicio, dataFim);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoResponse> atualizar(@PathVariable Long id,
                                                        @Valid @RequestBody TransacaoRequest request) {
        TransacaoResponse response = transacaoService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        transacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}/resumo")
    public ResponseEntity<ResumoFinanceiroResponse> obterResumoFinanceiro(@PathVariable Long usuarioId) {
        ResumoFinanceiroResponse response = transacaoService.obterResumoFinanceiro(usuarioId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{usuarioId}/resumo/periodo")
    public ResponseEntity<ResumoFinanceiroResponse> obterResumoFinanceiroPorPeriodo(
            @PathVariable Long usuarioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        ResumoFinanceiroResponse response = transacaoService.obterResumoFinanceiroPorPeriodo(usuarioId, dataInicio, dataFim);
        return ResponseEntity.ok(response);
    }
}
