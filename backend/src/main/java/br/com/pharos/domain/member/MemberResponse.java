package br.com.pharos.domain.member;

import java.util.UUID;

public record MemberResponse(
        UUID id,
        String name,
        String email,
        String phone,
        String notes,
        Boolean active
) {}