package br.com.pharos.service.loan;

import br.com.pharos.domain.book.Book;
import br.com.pharos.domain.loan.Loan;
import br.com.pharos.domain.loan.LoanMapper;
import br.com.pharos.domain.loan.LoanRequest;
import br.com.pharos.domain.loan.LoanResponse;
import br.com.pharos.domain.loan.LoanStatus;
import br.com.pharos.domain.member.Member;
import br.com.pharos.repository.book.BookRepository;
import br.com.pharos.repository.loan.LoanRepository;
import br.com.pharos.repository.member.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public LoanResponse create(LoanRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new IllegalStateException("No copies available for this book");
        }

        if (loanRepository.existsByMemberIdAndBookIdAndStatus(
                request.memberId(), request.bookId(), LoanStatus.ACTIVE)) {
            throw new IllegalStateException("Member already has an active loan for this book");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        return LoanMapper.toResponse(loanRepository.save(LoanMapper.toEntity(request, member, book)));
    }

    public LoanResponse returnBook(UUID id) {
        Loan loan = getById(id);

        if (loan.getStatus() == LoanStatus.RETURNED) {
            throw new IllegalStateException("Book already returned");
        }

        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);

        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return LoanMapper.toResponse(loanRepository.save(loan));
    }

    public LoanResponse renew(UUID id, LocalDate newDueDate) {
        Loan loan = getById(id);

        if (loan.getStatus() != LoanStatus.ACTIVE && loan.getStatus() != LoanStatus.OVERDUE) {
            throw new IllegalStateException("Only active or overdue loans can be renewed");
        }

        loan.setDueDate(newDueDate);
        loan.setStatus(LoanStatus.RENEWED);

        return LoanMapper.toResponse(loanRepository.save(loan));
    }

    public List<LoanResponse> findAll() {
        return loanRepository.findAll()
                .stream()
                .map(LoanMapper::toResponse)
                .toList();
    }

    public LoanResponse findById(UUID id) {
        return LoanMapper.toResponse(getById(id));
    }

    public List<LoanResponse> findByMember(UUID memberId) {
        return loanRepository.findByMemberId(memberId)
                .stream()
                .map(LoanMapper::toResponse)
                .toList();
    }

    public List<LoanResponse> findByBook(UUID bookId) {
        return loanRepository.findByBookId(bookId)
                .stream()
                .map(LoanMapper::toResponse)
                .toList();
    }

    public List<LoanResponse> findByStatus(LoanStatus status) {
        return loanRepository.findByStatus(status)
                .stream()
                .map(LoanMapper::toResponse)
                .toList();
    }

    public List<LoanResponse> findOverdue() {
        // Atualiza status para OVERDUE antes de retornar
        List<Loan> overdue = loanRepository
                .findByDueDateBeforeAndStatus(LocalDate.now(), LoanStatus.ACTIVE);

        overdue.forEach(loan -> loan.setStatus(LoanStatus.OVERDUE));
        loanRepository.saveAll(overdue);

        return loanRepository.findByStatus(LoanStatus.OVERDUE)
                .stream()
                .map(LoanMapper::toResponse)
                .toList();
    }

    private Loan getById(UUID id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found"));
    }
}