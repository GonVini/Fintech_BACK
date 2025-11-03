package br.com.fiap.fintech.dto;

public record ValidacaoResponse(
        Long id,
        Long usuarioId,
        Boolean status) {
}
