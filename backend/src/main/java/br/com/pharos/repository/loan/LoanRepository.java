package br.com.pharos.repository.loan;

import br.com.pharos.domain.loan.Loan;
import br.com.pharos.domain.loan.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {

    List<Loan> findByStatus(LoanStatus status);
    List<Loan> findByMemberId(UUID memberId);
    List<Loan> findByBookId(UUID bookId);
    List<Loan> findByMemberIdAndStatus(UUID memberId, LoanStatus status);
    List<Loan> findByDueDateBeforeAndStatus(LocalDate date, LoanStatus status);
    boolean existsByMemberIdAndBookIdAndStatus(UUID memberId, UUID bookId, LoanStatus status);

}