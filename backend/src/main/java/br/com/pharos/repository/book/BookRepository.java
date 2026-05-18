package br.com.pharos.repository.book;

import br.com.pharos.domain.book.Book;
import br.com.pharos.domain.book.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findByActiveTrue();
    List<Book> findByTitleContainingIgnoreCaseAndActiveTrue(String title);
    List<Book> findByAuthorContainingIgnoreCaseAndActiveTrue(String author);
    List<Book> findByCategoryAndActiveTrue(BookCategory category);
    List<Book> findByAvailableCopiesGreaterThanAndActiveTrue(Integer copies);
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);

}