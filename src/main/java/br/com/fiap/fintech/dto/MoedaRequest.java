package br.com.fiap.fintech.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MoedaRequest(
        @NotBlank @Size(max = 15) String simbolo,
        @NotBlank @Size(max = 80) String nome,
        @NotNull @DecimalMin("0.0") BigDecimal valorAtual,
        @NotNull LocalDateTime dataAtualizacao) {
}
