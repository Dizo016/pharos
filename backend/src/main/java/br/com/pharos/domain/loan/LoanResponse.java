package br.com.pharos.domain.loan;

import br.com.pharos.domain.book.BookResponse;
import br.com.pharos.domain.member.MemberResponse;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record LoanResponse(
        UUID id,
        MemberResponse member,
        BookResponse book,
        LocalDate loanDate,
        LocalDate dueDate,
        LocalDate returnDate,
        LoanStatus status,
        String notes,
        Instant createdAt
) {}