package br.com.pharos.domain.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BookRequest(
        @NotBlank String title,
        @NotBlank String author,
        String isbn,
        String publisher,
        Integer publishedYear,
        @NotNull BookCategory category,
        @NotNull @Positive Integer totalCopies,
        String description
) {}