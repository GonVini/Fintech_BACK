package br.com.fiap.fintech.repository;

import br.com.fiap.fintech.model.Account;
import br.com.fiap.fintech.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount(Account account);
    List<Transaction> findByAccountAndTransactionDateBetween(Account account, LocalDate start, LocalDate end);
}
