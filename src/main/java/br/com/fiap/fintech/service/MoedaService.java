package br.com.fiap.fintech.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.fintech.dto.MoedaRequest;
import br.com.fiap.fintech.dto.MoedaResponse;
import br.com.fiap.fintech.exception.ResourceNotFoundException;
import br.com.fiap.fintech.model.Moeda;
import br.com.fiap.fintech.repository.MoedaRepository;

@Service
@Transactional
public class MoedaService {

    private final MoedaRepository moedaRepository;

    public MoedaService(MoedaRepository moedaRepository) {
        this.moedaRepository = moedaRepository;
    }

    @Transactional(readOnly = true)
    public List<MoedaResponse> listar() {
        return moedaRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public MoedaResponse buscarPorId(Long id) {
        Moeda moeda = moedaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moeda não encontrada"));
        return mapToResponse(moeda);
    }

    public MoedaResponse criar(MoedaRequest request) {
        moedaRepository.findBySimboloIgnoreCase(request.simbolo())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Moeda já cadastrada");
                });

        Moeda moeda = new Moeda();
        moeda.setSimbolo(request.simbolo());
        moeda.setNome(request.nome());
        moeda.setValorAtual(request.valorAtual());
        moeda.setDataAtualizacao(request.dataAtualizacao());

        Moeda salvo = moedaRepository.save(moeda);
        return mapToResponse(salvo);
    }

    public MoedaResponse atualizar(Long id, MoedaRequest request) {
        Moeda moeda = moedaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moeda não encontrada"));

        moedaRepository.findBySimboloIgnoreCase(request.simbolo())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Moeda já cadastrada");
                });

        moeda.setSimbolo(request.simbolo());
        moeda.setNome(request.nome());
        moeda.setValorAtual(request.valorAtual());
        moeda.setDataAtualizacao(request.dataAtualizacao());

        return mapToResponse(moeda);
    }

    public void remover(Long id) {
        Moeda moeda = moedaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moeda não encontrada"));
        moedaRepository.delete(moeda);
    }

    private MoedaResponse mapToResponse(Moeda moeda) {
        return new MoedaResponse(
                moeda.getId(),
                moeda.getSimbolo(),
                moeda.getNome(),
                moeda.getValorAtual(),
                moeda.getDataAtualizacao());
    }
}
