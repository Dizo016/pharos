package br.com.pharos.domain.loan;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record LoanRequest(
        @NotNull UUID memberId,
        @NotNull UUID bookId,
        @NotNull @Future LocalDate dueDate,
        String notes
) {}