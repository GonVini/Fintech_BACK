package br.com.fiap.fintech.dto;

import java.time.LocalDateTime;

public record NotificacaoResponse(
        Long id,
        Long usuarioId,
        String descricao,
        LocalDateTime dataPublicacao) {
}
