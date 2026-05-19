package br.com.pharos.service.loan;

import br.com.pharos.domain.book.Book;
import br.com.pharos.domain.book.BookCategory;
import br.com.pharos.domain.loan.LoanRequest;
import br.com.pharos.domain.loan.LoanResponse;
import br.com.pharos.domain.loan.LoanStatus;
import br.com.pharos.domain.member.Member;
import br.com.pharos.repository.book.BookRepository;
import br.com.pharos.repository.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class LoanServiceTest {

    @Autowired
    private LoanService loanService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    private Member savedMember;
    private Book savedBook;

    @BeforeEach
    void setUp() {
        savedMember = memberRepository.save(Member.builder()
                .name("João Leitor")
                .email("joao@email.com")
                .build());

        savedBook = bookRepository.save(Book.builder()
                .title("Dom Casmurro")
                .author("Machado de Assis")
                .category(BookCategory.FICTION)
                .totalCopies(2)
                .availableCopies(2)
                .build());
    }

    @Test
    void shouldCreateLoan() {
        var request = new LoanRequest(
                savedMember.getId(),
                savedBook.getId(),
                LocalDate.now().plusDays(14),
                null
        );

        LoanResponse response = loanService.create(request);

        assertThat(response.id()).isNotNull();
        assertThat(response.member().id()).isEqualTo(savedMember.getId());
        assertThat(response.book().id()).isEqualTo(savedBook.getId());
        assertThat(response.status()).isEqualTo(LoanStatus.ACTIVE);
        assertThat(response.loanDate()).isEqualTo(LocalDate.now());
        assertThat(response.returnDate()).isNull();
    }

    @Test
    void shouldDecrementAvailableCopiesOnLoan() {
        var request = new LoanRequest(
                savedMember.getId(),
                savedBook.getId(),
                LocalDate.now().plusDays(14),
                null
        );

        loanService.create(request);

        Book updated = bookRepository.findById(savedBook.getId()).orElseThrow();
        assertThat(updated.getAvailableCopies()).isEqualTo(1);
    }

    @Test
    void shouldThrowWhenNoCopiesAvailable() {
        savedBook.setAvailableCopies(0);
        bookRepository.save(savedBook);

        var request = new LoanRequest(
                savedMember.getId(),
                savedBook.getId(),
                LocalDate.now().plusDays(14),
                null
        );

        assertThatThrownBy(() -> loanService.create(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("No copies available");
    }

    @Test
    void shouldThrowWhenMemberAlreadyHasActiveLoanForSameBook() {
        var request = new LoanRequest(
                savedMember.getId(),
                savedBook.getId(),
                LocalDate.now().plusDays(14),
                null
        );
        loanService.create(request);

        assertThatThrownBy(() -> loanService.create(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("active loan");
    }

    @Test
    void shouldReturnBook() {
        var request = new LoanRequest(
                savedMember.getId(),
                savedBook.getId(),
                LocalDate.now().plusDays(14),
                null
        );
        LoanResponse loan = loanService.create(request);

        LoanResponse returned = loanService.returnBook(loan.id());

        assertThat(returned.status()).isEqualTo(LoanStatus.RETURNED);
        assertThat(returned.returnDate()).isEqualTo(LocalDate.now());
    }

    @Test
    void shouldIncrementAvailableCopiesOnReturn() {
        var request = new LoanRequest(
                savedMember.getId(),
                savedBook.getId(),
                LocalDate.now().plusDays(14),
                null
        );
        LoanResponse loan = loanService.create(request);

        loanService.returnBook(loan.id());

        Book updated = bookRepository.findById(savedBook.getId()).orElseThrow();
        assertThat(updated.getAvailableCopies()).isEqualTo(2);
    }

    @Test
    void shouldThrowWhenReturningAlreadyReturnedLoan() {
        var request = new LoanRequest(
                savedMember.getId(),
                savedBook.getId(),
                LocalDate.now().plusDays(14),
                null
        );
        LoanResponse loan = loanService.create(request);
        loanService.returnBook(loan.id());

        assertThatThrownBy(() -> loanService.returnBook(loan.id()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("already returned");
    }

    @Test
    void shouldRenewLoan() {
        var request = new LoanRequest(
                savedMember.getId(),
                savedBook.getId(),
                LocalDate.now().plusDays(14),
                null
        );
        LoanResponse loan = loanService.create(request);
        LocalDate newDueDate = LocalDate.now().plusDays(28);

        LoanResponse renewed = loanService.renew(loan.id(), newDueDate);

        assertThat(renewed.status()).isEqualTo(LoanStatus.RENEWED);
        assertThat(renewed.dueDate()).isEqualTo(newDueDate);
    }

    @Test
    void shouldFindLoansByMember() {
        var request = new LoanRequest(
                savedMember.getId(),
                savedBook.getId(),
                LocalDate.now().plusDays(14),
                null
        );
        loanService.create(request);

        var results = loanService.findByMember(savedMember.getId());

        assertThat(results).hasSize(1);
        assertThat(results.get(0).member().id()).isEqualTo(savedMember.getId());
    }

    @Test
    void shouldFindLoansByStatus() {
        var request = new LoanRequest(
                savedMember.getId(),
                savedBook.getId(),
                LocalDate.now().plusDays(14),
                null
        );
        loanService.create(request);

        var active = loanService.findByStatus(LoanStatus.ACTIVE);

        assertThat(active).hasSize(1);
        assertThat(active.get(0).status()).isEqualTo(LoanStatus.ACTIVE);
    }
}