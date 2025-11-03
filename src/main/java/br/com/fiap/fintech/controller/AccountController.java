package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dto.AccountRequest;
import br.com.fiap.fintech.model.Account;
import br.com.fiap.fintech.model.User;
import br.com.fiap.fintech.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> list() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Account> findByUser(@PathVariable Long userId) {
        return accountService.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Account> create(@Valid @RequestBody AccountRequest request) {
        Account account = new Account();
        account.setInstitution(request.institution());
        account.setAccountType(request.accountType());
        account.setAccountNumber(request.accountNumber());
        account.setBalance(request.balance());
        Account created = accountService.create(account, request.userId());
        return ResponseEntity.created(URI.create("/api/accounts/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public Account update(@PathVariable Long id, @Valid @RequestBody AccountRequest request) {
        Account account = new Account();
        account.setInstitution(request.institution());
        account.setAccountType(request.accountType());
        account.setAccountNumber(request.accountNumber());
        account.setBalance(request.balance());
        User user = new User();
        user.setId(request.userId());
        account.setUser(user);
        return accountService.update(id, account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
