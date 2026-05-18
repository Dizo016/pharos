package br.com.pharos.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotBlank String name,
        @NotBlank String login,
        @NotBlank String password,
        @NotNull UserRole userRole
) {}