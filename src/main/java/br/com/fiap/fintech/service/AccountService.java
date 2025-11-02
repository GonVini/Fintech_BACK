package br.com.fiap.fintech.service;

import br.com.fiap.fintech.exception.ResourceNotFoundException;
import br.com.fiap.fintech.model.Account;
import br.com.fiap.fintech.model.User;
import br.com.fiap.fintech.repository.AccountRepository;
import br.com.fiap.fintech.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada: id=" + id));
    }

    @Transactional(readOnly = true)
    public List<Account> findByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: id=" + userId));
        return accountRepository.findByUser(user);
    }

    public Account create(Account account, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: id=" + userId));
        account.setUser(user);
        return accountRepository.save(account);
    }

    public Account update(Long id, Account accountUpdate) {
        Account existing = findById(id);
        existing.setInstitution(accountUpdate.getInstitution());
        existing.setAccountType(accountUpdate.getAccountType());
        existing.setAccountNumber(accountUpdate.getAccountNumber());
        existing.setBalance(accountUpdate.getBalance());
        if (accountUpdate.getUser() != null && accountUpdate.getUser().getId() != null) {
            Long userId = accountUpdate.getUser().getId();
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: id=" + userId));
            existing.setUser(user);
        }
        return accountRepository.save(existing);
    }

    public void delete(Long id) {
        Account existing = findById(id);
        accountRepository.delete(existing);
    }
}
