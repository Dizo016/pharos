package br.com.pharos.domain.book;

import java.util.UUID;

public record BookResponse(
        UUID id,
        String title,
        String author,
        String isbn,
        String publisher,
        Integer publishedYear,
        BookCategory category,
        Integer totalCopies,
        Integer availableCopies,
        String description,
        Boolean active
) {}