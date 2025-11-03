package br.com.fiap.fintech.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.fintech.dto.NotificacaoRequest;
import br.com.fiap.fintech.dto.NotificacaoResponse;
import br.com.fiap.fintech.exception.ResourceNotFoundException;
import br.com.fiap.fintech.model.Notificacao;
import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.repository.NotificacaoRepository;
import br.com.fiap.fintech.repository.UsuarioRepository;

@Service
@Transactional
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository, UsuarioRepository usuarioRepository) {
        this.notificacaoRepository = notificacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<NotificacaoResponse> listar() {
        return notificacaoRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<NotificacaoResponse> listarPorUsuario(Long usuarioId) {
        return notificacaoRepository.findByUsuarioId(usuarioId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public NotificacaoResponse buscarPorId(Long id) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificação não encontrada"));
        return mapToResponse(notificacao);
    }

    public NotificacaoResponse criar(NotificacaoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Notificacao notificacao = new Notificacao();
        notificacao.setUsuario(usuario);
        notificacao.setDescricao(request.descricao());
        notificacao.setDataPublicacao(request.dataPublicacao());

        Notificacao salvo = notificacaoRepository.save(notificacao);
        return mapToResponse(salvo);
    }

    public NotificacaoResponse atualizar(Long id, NotificacaoRequest request) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificação não encontrada"));

        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        notificacao.setUsuario(usuario);
        notificacao.setDescricao(request.descricao());
        notificacao.setDataPublicacao(request.dataPublicacao());

        return mapToResponse(notificacao);
    }

    public void remover(Long id) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificação não encontrada"));
        notificacaoRepository.delete(notificacao);
    }

    private NotificacaoResponse mapToResponse(Notificacao notificacao) {
        return new NotificacaoResponse(
                notificacao.getId(),
                notificacao.getUsuario().getId(),
                notificacao.getDescricao(),
                notificacao.getDataPublicacao());
    }
}
