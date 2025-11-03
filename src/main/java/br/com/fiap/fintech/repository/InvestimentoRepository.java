package br.com.fiap.fintech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.fintech.model.Investimento;

public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {

    List<Investimento> findByUsuarioId(Long usuarioId);
}
