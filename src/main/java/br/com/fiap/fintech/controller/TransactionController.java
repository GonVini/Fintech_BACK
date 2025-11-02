package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dto.TransactionRequest;
import br.com.fiap.fintech.model.Account;
import br.com.fiap.fintech.model.Transaction;
import br.com.fiap.fintech.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> list() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable Long id) {
        return transactionService.findById(id);
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> findByAccount(
            @PathVariable Long accountId,
            @RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        if (start != null && end != null) {
            return transactionService.findByAccountIdAndPeriod(accountId, start, end);
        }
        return transactionService.findByAccountId(accountId);
    }

    @PostMapping
    public ResponseEntity<Transaction> create(@Valid @RequestBody TransactionRequest request) {
        Transaction transaction = new Transaction();
        transaction.setType(request.type());
        transaction.setAmount(request.amount());
        transaction.setCategory(request.category());
        transaction.setDescription(request.description());
        transaction.setTransactionDate(request.transactionDate());
        Transaction created = transactionService.create(transaction, request.accountId());
        return ResponseEntity.created(URI.create("/api/transactions/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public Transaction update(@PathVariable Long id, @Valid @RequestBody TransactionRequest request) {
        Transaction transaction = new Transaction();
        transaction.setType(request.type());
        transaction.setAmount(request.amount());
        transaction.setCategory(request.category());
        transaction.setDescription(request.description());
        transaction.setTransactionDate(request.transactionDate());
        Account account = new Account();
        account.setId(request.accountId());
        transaction.setAccount(account);
        return transactionService.update(id, transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
