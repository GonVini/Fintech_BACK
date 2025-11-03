package br.com.fiap.fintech.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.fintech.dto.InvestimentoRequest;
import br.com.fiap.fintech.dto.InvestimentoResponse;
import br.com.fiap.fintech.exception.ResourceNotFoundException;
import br.com.fiap.fintech.model.Investimento;
import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.repository.InvestimentoRepository;
import br.com.fiap.fintech.repository.UsuarioRepository;

@Service
@Transactional
public class InvestimentoService {

    private final InvestimentoRepository investimentoRepository;
    private final UsuarioRepository usuarioRepository;

    public InvestimentoService(InvestimentoRepository investimentoRepository, UsuarioRepository usuarioRepository) {
        this.investimentoRepository = investimentoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<InvestimentoResponse> listar() {
        return investimentoRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<InvestimentoResponse> listarPorUsuario(Long usuarioId) {
        return investimentoRepository.findByUsuarioId(usuarioId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public InvestimentoResponse buscarPorId(Long id) {
        Investimento investimento = investimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investimento não encontrado"));
        return mapToResponse(investimento);
    }

    public InvestimentoResponse criar(InvestimentoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Investimento investimento = new Investimento();
        investimento.setUsuario(usuario);
        investimento.setNome(request.nome());
        investimento.setValorAplicado(request.valorAplicado());
        investimento.setDataAplicacao(request.dataAplicacao());
        investimento.setRetornoPrevisto(request.retornoPrevisto());

        Investimento salvo = investimentoRepository.save(investimento);
        return mapToResponse(salvo);
    }

    public InvestimentoResponse atualizar(Long id, InvestimentoRequest request) {
        Investimento investimento = investimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investimento não encontrado"));

        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        investimento.setUsuario(usuario);
        investimento.setNome(request.nome());
        investimento.setValorAplicado(request.valorAplicado());
        investimento.setDataAplicacao(request.dataAplicacao());
        investimento.setRetornoPrevisto(request.retornoPrevisto());

        return mapToResponse(investimento);
    }

    public void remover(Long id) {
        Investimento investimento = investimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investimento não encontrado"));
        investimentoRepository.delete(investimento);
    }

    private InvestimentoResponse mapToResponse(Investimento investimento) {
        return new InvestimentoResponse(
                investimento.getId(),
                investimento.getUsuario().getId(),
                investimento.getNome(),
                investimento.getValorAplicado(),
                investimento.getDataAplicacao(),
                investimento.getRetornoPrevisto());
    }
}
