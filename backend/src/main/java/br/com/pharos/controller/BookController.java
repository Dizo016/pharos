package br.com.pharos.controller;

import br.com.pharos.domain.book.BookCategory;
import br.com.pharos.domain.book.BookRequest;
import br.com.pharos.domain.book.BookResponse;
import br.com.pharos.service.book.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody @Valid BookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookResponse>> findAvailable() {
        return ResponseEntity.ok(bookService.findAvailable());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<BookResponse>> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @GetMapping("/search/author")
    public ResponseEntity<List<BookResponse>> findByAuthor(@RequestParam String author) {
        return ResponseEntity.ok(bookService.findByAuthor(author));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<BookResponse>> findByCategory(@PathVariable BookCategory category) {
        return ResponseEntity.ok(bookService.findByCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid BookRequest request) {
        return ResponseEntity.ok(bookService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable UUID id) {
        bookService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}