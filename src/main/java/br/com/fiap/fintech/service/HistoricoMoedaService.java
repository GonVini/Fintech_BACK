package br.com.fiap.fintech.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.fintech.dto.HistoricoMoedaRequest;
import br.com.fiap.fintech.dto.HistoricoMoedaResponse;
import br.com.fiap.fintech.exception.ResourceNotFoundException;
import br.com.fiap.fintech.model.HistoricoMoeda;
import br.com.fiap.fintech.model.Moeda;
import br.com.fiap.fintech.repository.HistoricoMoedaRepository;
import br.com.fiap.fintech.repository.MoedaRepository;

@Service
@Transactional
public class HistoricoMoedaService {

    private final HistoricoMoedaRepository historicoMoedaRepository;
    private final MoedaRepository moedaRepository;

    public HistoricoMoedaService(HistoricoMoedaRepository historicoMoedaRepository, MoedaRepository moedaRepository) {
        this.historicoMoedaRepository = historicoMoedaRepository;
        this.moedaRepository = moedaRepository;
    }

    @Transactional(readOnly = true)
    public List<HistoricoMoedaResponse> listar() {
        return historicoMoedaRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<HistoricoMoedaResponse> listarPorMoeda(Long moedaId) {
        return historicoMoedaRepository.findByMoedaId(moedaId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public HistoricoMoedaResponse buscarPorId(Long id) {
        HistoricoMoeda historico = historicoMoedaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Histórico de moeda não encontrado"));
        return mapToResponse(historico);
    }

    public HistoricoMoedaResponse criar(HistoricoMoedaRequest request) {
        Moeda moeda = moedaRepository.findById(request.moedaId())
                .orElseThrow(() -> new ResourceNotFoundException("Moeda não encontrada"));

        HistoricoMoeda historico = new HistoricoMoeda();
        historico.setMoeda(moeda);
        historico.setValor(request.valor());
        historico.setDataRegistro(request.dataRegistro());

        HistoricoMoeda salvo = historicoMoedaRepository.save(historico);
        return mapToResponse(salvo);
    }

    public HistoricoMoedaResponse atualizar(Long id, HistoricoMoedaRequest request) {
        HistoricoMoeda historico = historicoMoedaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Histórico de moeda não encontrado"));

        Moeda moeda = moedaRepository.findById(request.moedaId())
                .orElseThrow(() -> new ResourceNotFoundException("Moeda não encontrada"));

        historico.setMoeda(moeda);
        historico.setValor(request.valor());
        historico.setDataRegistro(request.dataRegistro());

        return mapToResponse(historico);
    }

    public void remover(Long id) {
        HistoricoMoeda historico = historicoMoedaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Histórico de moeda não encontrado"));
        historicoMoedaRepository.delete(historico);
    }

    private HistoricoMoedaResponse mapToResponse(HistoricoMoeda historico) {
        return new HistoricoMoedaResponse(
                historico.getId(),
                historico.getMoeda().getId(),
                historico.getValor(),
                historico.getDataRegistro());
    }
}
