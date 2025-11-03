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

import br.com.fiap.fintech.dto.ValidacaoRequest;
import br.com.fiap.fintech.dto.ValidacaoResponse;
import br.com.fiap.fintech.service.ValidacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/validacoes")
@Validated
public class ValidacaoController {

    private final ValidacaoService validacaoService;

    public ValidacaoController(ValidacaoService validacaoService) {
        this.validacaoService = validacaoService;
    }

    @GetMapping
    public ResponseEntity<List<ValidacaoResponse>> listar() {
        return ResponseEntity.ok(validacaoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValidacaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(validacaoService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ValidacaoResponse> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(validacaoService.buscarPorUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<ValidacaoResponse> criar(@Valid @RequestBody ValidacaoRequest request) {
        ValidacaoResponse response = validacaoService.criar(request);
        return ResponseEntity.created(URI.create("/api/validacoes/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ValidacaoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ValidacaoRequest request) {
        return ResponseEntity.ok(validacaoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        validacaoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
