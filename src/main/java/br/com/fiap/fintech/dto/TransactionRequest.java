package br.com.fiap.fintech.dto;

import br.com.fiap.fintech.model.Transaction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequest(
        @NotNull Transaction.Type type,
        @NotNull BigDecimal amount,
        @NotBlank @Size(max = 100) String category,
        @NotBlank @Size(max = 255) String description,
        @NotNull LocalDate transactionDate,
        @NotNull Long accountId
) {
}
