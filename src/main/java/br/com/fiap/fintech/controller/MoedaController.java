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
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.fintech.dto.MoedaRequest;
import br.com.fiap.fintech.dto.MoedaResponse;
import br.com.fiap.fintech.service.MoedaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/moedas")
@Validated
public class MoedaController {

    private final MoedaService moedaService;

    public MoedaController(MoedaService moedaService) {
        this.moedaService = moedaService;
    }

    @GetMapping
    public ResponseEntity<List<MoedaResponse>> listar() {
        return ResponseEntity.ok(moedaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoedaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(moedaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MoedaResponse> criar(@Valid @RequestBody MoedaRequest request) {
        MoedaResponse response = moedaService.criar(request);
        return ResponseEntity.created(URI.create("/api/moedas/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MoedaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody MoedaRequest request) {
        return ResponseEntity.ok(moedaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        moedaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
