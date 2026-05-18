package br.com.pharos.domain.user;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String login,
        UserRole userRole,
        Boolean active
) {}