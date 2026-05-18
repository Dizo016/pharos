package br.com.pharos.service.user;

import br.com.pharos.domain.user.*;
import br.com.pharos.repository.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse create(UserRequest request) {
        if (repository.existsByLogin(request.login())) {
            throw new IllegalArgumentException("Login already in use");
        }
        String encodedPassword = passwordEncoder.encode(request.password());
        return UserMapper.toResponse(repository.save(UserMapper.toEntity(request, encodedPassword)));
    }

    public List<UserResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    public UserResponse findById(UUID id) {
        return UserMapper.toResponse(getById(id));
    }

    public UserResponse findByLogin(String login) {
        return repository.findByLogin(login)
                .map(UserMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public UserResponse update(UUID id, UserRequest request) {
        User existing = getById(id);
        existing.setName(request.name());
        existing.setLogin(request.login());
        existing.setUserRole(request.userRole());
        if (request.password() != null && !request.password().isBlank()) {
            existing.setPassword(passwordEncoder.encode(request.password()));
        }
        return UserMapper.toResponse(repository.save(existing));
    }

    public void deactivate(UUID id) {
        User user = getById(id);
        user.setActive(false);
        repository.save(user);
    }

    private User getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}