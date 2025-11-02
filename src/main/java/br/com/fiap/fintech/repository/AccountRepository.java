package br.com.fiap.fintech.repository;

import br.com.fiap.fintech.model.Account;
import br.com.fiap.fintech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUser(User user);
}
