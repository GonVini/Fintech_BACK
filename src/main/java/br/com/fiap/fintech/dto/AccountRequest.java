package br.com.fiap.fintech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record AccountRequest(
        @NotBlank @Size(max = 60) String institution,
        @NotBlank @Size(max = 30) String accountType,
        @NotBlank @Size(max = 25) String accountNumber,
        @NotNull BigDecimal balance,
        @NotNull Long userId
) {
}
