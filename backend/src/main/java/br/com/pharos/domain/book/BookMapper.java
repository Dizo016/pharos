package br.com.pharos.domain.book;

public class BookMapper {

    public static Book toEntity(BookRequest request) {
        return Book.builder()
                .title(request.title())
                .author(request.author())
                .isbn(request.isbn())
                .publisher(request.publisher())
                .publishedYear(request.publishedYear())
                .category(request.category())
                .totalCopies(request.totalCopies())
                .availableCopies(request.totalCopies())
                .description(request.description())
                .build();
    }

    public static BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublisher(),
                book.getPublishedYear(),
                book.getCategory(),
                book.getTotalCopies(),
                book.getAvailableCopies(),
                book.getDescription(),
                book.getActive()
        );
    }
}