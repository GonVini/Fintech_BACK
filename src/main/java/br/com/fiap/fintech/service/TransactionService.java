package br.com.fiap.fintech.service;

import br.com.fiap.fintech.exception.ResourceNotFoundException;
import br.com.fiap.fintech.model.Account;
import br.com.fiap.fintech.model.Transaction;
import br.com.fiap.fintech.repository.AccountRepository;
import br.com.fiap.fintech.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transação não encontrada: id=" + id));
    }

    @Transactional(readOnly = true)
    public List<Transaction> findByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada: id=" + accountId));
        return transactionRepository.findByAccount(account);
    }

    @Transactional(readOnly = true)
    public List<Transaction> findByAccountIdAndPeriod(Long accountId, LocalDate start, LocalDate end) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada: id=" + accountId));
        return transactionRepository.findByAccountAndTransactionDateBetween(account, start, end);
    }

    public Transaction create(Transaction transaction, Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada: id=" + accountId));
        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }

    public Transaction update(Long id, Transaction update) {
        Transaction existing = findById(id);
        existing.setType(update.getType());
        existing.setAmount(update.getAmount());
        existing.setCategory(update.getCategory());
        existing.setDescription(update.getDescription());
        existing.setTransactionDate(update.getTransactionDate());
        if (update.getAccount() != null && update.getAccount().getId() != null) {
            Long accountId = update.getAccount().getId();
            Account account = accountRepository.findById(accountId)
                    .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada: id=" + accountId));
            existing.setAccount(account);
        }
        return transactionRepository.save(existing);
    }

    public void delete(Long id) {
        Transaction existing = findById(id);
        transactionRepository.delete(existing);
    }
}
