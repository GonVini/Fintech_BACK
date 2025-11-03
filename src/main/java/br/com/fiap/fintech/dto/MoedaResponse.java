package br.com.fiap.fintech.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MoedaResponse(
        Long id,
        String simbolo,
        String nome,
        BigDecimal valorAtual,
        LocalDateTime dataAtualizacao) {
}
