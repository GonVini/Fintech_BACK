package br.com.fiap.fintech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.fintech.model.Validacao;

public interface ValidacaoRepository extends JpaRepository<Validacao, Long> {

    Optional<Validacao> findByUsuarioId(Long usuarioId);
}
