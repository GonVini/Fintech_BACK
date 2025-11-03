package br.com.fiap.fintech.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record InvestimentoRequest(
        @NotNull Long usuarioId,
        @NotBlank @Size(max = 120) String nome,
        @NotNull @DecimalMin(value = "0.0", inclusive = false) BigDecimal valorAplicado,
        @NotNull LocalDate dataAplicacao,
        @DecimalMin("0.0") BigDecimal retornoPrevisto) {
}
