package br.com.fiap.fintech.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NotificacaoRequest(
        @NotNull Long usuarioId,
        @NotBlank String descricao,
        @NotNull LocalDateTime dataPublicacao) {
}
