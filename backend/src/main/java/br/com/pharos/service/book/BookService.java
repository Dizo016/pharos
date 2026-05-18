package br.com.pharos.service.book;

import br.com.pharos.domain.book.Book;
import br.com.pharos.domain.book.BookCategory;
import br.com.pharos.domain.book.BookMapper;
import br.com.pharos.domain.book.BookRequest;
import br.com.pharos.domain.book.BookResponse;
import br.com.pharos.repository.book.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public BookResponse create(BookRequest request) {
        if (request.isbn() != null && repository.existsByIsbn(request.isbn())) {
            throw new IllegalArgumentException("ISBN already registered");
        }
        return BookMapper.toResponse(repository.save(BookMapper.toEntity(request)));
    }

    public List<BookResponse> findAll() {
        return repository.findByActiveTrue()
                .stream()
                .map(BookMapper::toResponse)
                .toList();
    }

    public BookResponse findById(UUID id) {
        return BookMapper.toResponse(getById(id));
    }

    public List<BookResponse> findByTitle(String title) {
        return repository.findByTitleContainingIgnoreCaseAndActiveTrue(title)
                .stream()
                .map(BookMapper::toResponse)
                .toList();
    }

    public List<BookResponse> findByAuthor(String author) {
        return repository.findByAuthorContainingIgnoreCaseAndActiveTrue(author)
                .stream()
                .map(BookMapper::toResponse)
                .toList();
    }

    public List<BookResponse> findByCategory(BookCategory category) {
        return repository.findByCategoryAndActiveTrue(category)
                .stream()
                .map(BookMapper::toResponse)
                .toList();
    }

    public List<BookResponse> findAvailable() {
        return repository.findByAvailableCopiesGreaterThanAndActiveTrue(0)
                .stream()
                .map(BookMapper::toResponse)
                .toList();
    }

    public BookResponse update(UUID id, BookRequest request) {
        Book existing = getById(id);
        int difference = request.totalCopies() - existing.getTotalCopies();
        existing.setTitle(request.title());
        existing.setAuthor(request.author());
        existing.setIsbn(request.isbn());
        existing.setPublisher(request.publisher());
        existing.setPublishedYear(request.publishedYear());
        existing.setCategory(request.category());
        existing.setTotalCopies(request.totalCopies());
        existing.setAvailableCopies(Math.max(0, existing.getAvailableCopies() + difference));
        existing.setDescription(request.description());
        return BookMapper.toResponse(repository.save(existing));
    }

    public void deactivate(UUID id) {
        Book book = getById(id);
        book.setActive(false);
        repository.save(book);
    }

    private Book getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }
}