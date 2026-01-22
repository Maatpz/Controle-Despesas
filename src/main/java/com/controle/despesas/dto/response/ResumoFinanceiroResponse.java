package com.controle.despesas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumoFinanceiroResponse {

    private Double totalReceitas;
    private Double totalDespesas;
    private Double saldo;

    public ResumoFinanceiroResponse(Double totalReceitas, Double totalDespesas) {
        this.totalReceitas = totalReceitas != null ? totalReceitas : 0.0;
        this.totalDespesas = totalDespesas != null ? totalDespesas : 0.0;
        this.saldo = this.totalReceitas - this.totalDespesas;
    }
}
