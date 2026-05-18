package br.com.pharos.domain.loan;

import br.com.pharos.domain.book.Book;
import br.com.pharos.domain.book.BookMapper;
import br.com.pharos.domain.member.Member;
import br.com.pharos.domain.member.MemberMapper;

import java.time.LocalDate;

public class LoanMapper {

    public static Loan toEntity(LoanRequest request, Member member, Book book) {
        return Loan.builder()
                .member(member)
                .book(book)
                .loanDate(LocalDate.now())
                .dueDate(request.dueDate())
                .status(LoanStatus.ACTIVE)
                .notes(request.notes())
                .build();
    }

    public static LoanResponse toResponse(Loan loan) {
        return new LoanResponse(
                loan.getId(),
                MemberMapper.toResponse(loan.getMember()),
                BookMapper.toResponse(loan.getBook()),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnDate(),
                loan.getStatus(),
                loan.getNotes(),
                loan.getCreatedAt()
        );
    }
}