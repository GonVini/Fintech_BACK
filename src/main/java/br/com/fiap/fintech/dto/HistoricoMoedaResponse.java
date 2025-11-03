package br.com.fiap.fintech.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record HistoricoMoedaResponse(
        Long id,
        Long moedaId,
        BigDecimal valor,
        LocalDateTime dataRegistro) {
}
