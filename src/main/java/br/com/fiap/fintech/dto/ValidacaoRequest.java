package br.com.fiap.fintech.dto;

import jakarta.validation.constraints.NotNull;

public record ValidacaoRequest(
        @NotNull Long usuarioId,
        @NotNull Boolean status) {
}
