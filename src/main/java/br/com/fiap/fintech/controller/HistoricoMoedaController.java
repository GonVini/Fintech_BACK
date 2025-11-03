package br.com.fiap.fintech.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.fintech.dto.HistoricoMoedaRequest;
import br.com.fiap.fintech.dto.HistoricoMoedaResponse;
import br.com.fiap.fintech.service.HistoricoMoedaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/historico-moedas")
@Validated
public class HistoricoMoedaController {

    private final HistoricoMoedaService historicoMoedaService;

    public HistoricoMoedaController(HistoricoMoedaService historicoMoedaService) {
        this.historicoMoedaService = historicoMoedaService;
    }

    @GetMapping
    public ResponseEntity<List<HistoricoMoedaResponse>> listar(
            @RequestParam(name = "moedaId", required = false) Long moedaId) {
        if (moedaId != null) {
            return ResponseEntity.ok(historicoMoedaService.listarPorMoeda(moedaId));
        }
        return ResponseEntity.ok(historicoMoedaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoMoedaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(historicoMoedaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<HistoricoMoedaResponse> criar(@Valid @RequestBody HistoricoMoedaRequest request) {
        HistoricoMoedaResponse response = historicoMoedaService.criar(request);
        return ResponseEntity.created(URI.create("/api/historico-moedas/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoricoMoedaResponse> atualizar(@PathVariable Long id,
            @Valid @RequestBody HistoricoMoedaRequest request) {
        return ResponseEntity.ok(historicoMoedaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        historicoMoedaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
