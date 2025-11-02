package br.com.fiap.fintech.service;

import br.com.fiap.fintech.exception.ResourceNotFoundException;
import br.com.fiap.fintech.model.User;
import br.com.fiap.fintech.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: id=" + id));
    }

    public User create(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(existing -> {
            throw new IllegalArgumentException("E-mail já cadastrado: " + existing.getEmail());
        });
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        User existing = findById(id);
        existing.setFullName(user.getFullName());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());
        return userRepository.save(existing);
    }

    public void delete(Long id) {
        User existing = findById(id);
        userRepository.delete(existing);
    }
}
