package br.com.fiap.fintech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.fintech.model.Moeda;

public interface MoedaRepository extends JpaRepository<Moeda, Long> {

    Optional<Moeda> findBySimboloIgnoreCase(String simbolo);
}
