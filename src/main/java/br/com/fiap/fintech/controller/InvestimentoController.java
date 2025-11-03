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

import br.com.fiap.fintech.dto.InvestimentoRequest;
import br.com.fiap.fintech.dto.InvestimentoResponse;
import br.com.fiap.fintech.service.InvestimentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/investimentos")
@Validated
public class InvestimentoController {

    private final InvestimentoService investimentoService;

    public InvestimentoController(InvestimentoService investimentoService) {
        this.investimentoService = investimentoService;
    }

    @GetMapping
    public ResponseEntity<List<InvestimentoResponse>> listar(
            @RequestParam(name = "usuarioId", required = false) Long usuarioId) {
        if (usuarioId != null) {
            return ResponseEntity.ok(investimentoService.listarPorUsuario(usuarioId));
        }
        return ResponseEntity.ok(investimentoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestimentoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(investimentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<InvestimentoResponse> criar(@Valid @RequestBody InvestimentoRequest request) {
        InvestimentoResponse response = investimentoService.criar(request);
        return ResponseEntity.created(URI.create("/api/investimentos/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestimentoResponse> atualizar(@PathVariable Long id,
            @Valid @RequestBody InvestimentoRequest request) {
        return ResponseEntity.ok(investimentoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        investimentoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
