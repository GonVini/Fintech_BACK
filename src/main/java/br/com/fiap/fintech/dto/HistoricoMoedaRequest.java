package br.com.fiap.fintech.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record HistoricoMoedaRequest(
        @NotNull Long moedaId,
        @NotNull @DecimalMin("0.0") BigDecimal valor,
        @NotNull LocalDateTime dataRegistro) {
}
