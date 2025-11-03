package br.com.fiap.fintech.dto;

import java.time.LocalDateTime;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        Boolean verificado,
        LocalDateTime dataRegistro) {
}
