package br.com.fiap.fintech.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvestimentoResponse(
        Long id,
        Long usuarioId,
        String nome,
        BigDecimal valorAplicado,
        LocalDate dataAplicacao,
        BigDecimal retornoPrevisto) {
}
