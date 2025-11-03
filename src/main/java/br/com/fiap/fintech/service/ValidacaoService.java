package br.com.fiap.fintech.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.fintech.dto.ValidacaoRequest;
import br.com.fiap.fintech.dto.ValidacaoResponse;
import br.com.fiap.fintech.exception.ResourceNotFoundException;
import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.model.Validacao;
import br.com.fiap.fintech.repository.UsuarioRepository;
import br.com.fiap.fintech.repository.ValidacaoRepository;

@Service
@Transactional
public class ValidacaoService {

    private final ValidacaoRepository validacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public ValidacaoService(ValidacaoRepository validacaoRepository, UsuarioRepository usuarioRepository) {
        this.validacaoRepository = validacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<ValidacaoResponse> listar() {
        return validacaoRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ValidacaoResponse buscarPorId(Long id) {
        Validacao validacao = validacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Validação não encontrada"));
        return mapToResponse(validacao);
    }

    @Transactional(readOnly = true)
    public ValidacaoResponse buscarPorUsuario(Long usuarioId) {
        Validacao validacao = validacaoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Validação não encontrada"));
        return mapToResponse(validacao);
    }

    public ValidacaoResponse criar(ValidacaoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        validacaoRepository.findByUsuarioId(usuario.getId())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Usuário já possui registro de validação");
                });

        Validacao validacao = new Validacao();
        validacao.setUsuario(usuario);
        validacao.setStatus(request.status());

        Validacao salvo = validacaoRepository.save(validacao);
        return mapToResponse(salvo);
    }

    public ValidacaoResponse atualizar(Long id, ValidacaoRequest request) {
        Validacao validacao = validacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Validação não encontrada"));

        if (!validacao.getUsuario().getId().equals(request.usuarioId())) {
            Usuario usuario = usuarioRepository.findById(request.usuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

            validacaoRepository.findByUsuarioId(usuario.getId())
                    .filter(existing -> !existing.getId().equals(id))
                    .ifPresent(existing -> {
                        throw new IllegalArgumentException("Usuário já possui registro de validação");
                    });

            validacao.setUsuario(usuario);
        }

        validacao.setStatus(request.status());

        return mapToResponse(validacao);
    }

    public void remover(Long id) {
        Validacao validacao = validacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Validação não encontrada"));
        validacaoRepository.delete(validacao);
    }

    private ValidacaoResponse mapToResponse(Validacao validacao) {
        return new ValidacaoResponse(
                validacao.getId(),
                validacao.getUsuario().getId(),
                validacao.getStatus());
    }
}
