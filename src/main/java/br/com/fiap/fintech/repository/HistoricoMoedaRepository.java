package br.com.fiap.fintech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.fintech.model.HistoricoMoeda;

public interface HistoricoMoedaRepository extends JpaRepository<HistoricoMoeda, Long> {

    List<HistoricoMoeda> findByMoedaId(Long moedaId);
}
