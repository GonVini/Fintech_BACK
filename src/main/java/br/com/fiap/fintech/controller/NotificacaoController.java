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

import br.com.fiap.fintech.dto.NotificacaoRequest;
import br.com.fiap.fintech.dto.NotificacaoResponse;
import br.com.fiap.fintech.service.NotificacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notificacoes")
@Validated
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @GetMapping
    public ResponseEntity<List<NotificacaoResponse>> listar(@RequestParam(name = "usuarioId", required = false) Long usuarioId) {
        if (usuarioId != null) {
            return ResponseEntity.ok(notificacaoService.listarPorUsuario(usuarioId));
        }
        return ResponseEntity.ok(notificacaoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(notificacaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<NotificacaoResponse> criar(@Valid @RequestBody NotificacaoRequest request) {
        NotificacaoResponse response = notificacaoService.criar(request);
        return ResponseEntity.created(URI.create("/api/notificacoes/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacaoResponse> atualizar(@PathVariable Long id,
            @Valid @RequestBody NotificacaoRequest request) {
        return ResponseEntity.ok(notificacaoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        notificacaoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
