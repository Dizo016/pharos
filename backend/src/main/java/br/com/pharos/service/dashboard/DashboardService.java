package br.com.pharos.service.dashboard;

import br.com.pharos.domain.dashboard.DashboardResponse;
import br.com.pharos.domain.dashboard.DashboardResponse.MonthlyLoans;
import br.com.pharos.domain.loan.Loan;
import br.com.pharos.domain.loan.LoanStatus;
import br.com.pharos.repository.book.BookRepository;
import br.com.pharos.repository.loan.LoanRepository;
import br.com.pharos.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public DashboardResponse getDashboard() {
        LocalDate today = LocalDate.now();
        List<Loan> allLoans = loanRepository.findAll();

        // Métricas de membros
        long totalMembers = memberRepository.findByActiveTrue().size();

        // Métricas de empréstimos
        long activeLoans = allLoans.stream()
                .filter(l -> l.getStatus() == LoanStatus.ACTIVE
                        || l.getStatus() == LoanStatus.RENEWED)
                .count();

        long overdueLoans = allLoans.stream()
                .filter(l -> (l.getStatus() == LoanStatus.ACTIVE
                        || l.getStatus() == LoanStatus.RENEWED)
                        && l.getDueDate().isBefore(today))
                .count();

        long loansToday = allLoans.stream()
                .filter(l -> l.getLoanDate().equals(today))
                .count();

        // Métricas de acervo
        long totalBooks = bookRepository.findByActiveTrue().size();
        long availableBooks = bookRepository.findByAvailableCopiesGreaterThanAndActiveTrue(0).size();

        // Empréstimos dos últimos 6 meses
        List<MonthlyLoans> lastSixMonths = buildLastSixMonths(allLoans, today);

        // Livro mais emprestado
        String mostBorrowedBook = allLoans.stream()
                .collect(Collectors.groupingBy(
                        l -> l.getBook().getTitle(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        // Membro mais ativo
        String mostActiveMember = allLoans.stream()
                .collect(Collectors.groupingBy(
                        l -> l.getMember().getName(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> e.getKey() + " (" + e.getValue() + " empréstimos)")
                .orElse(null);

        return new DashboardResponse(
                totalMembers,
                activeLoans,
                overdueLoans,
                availableBooks,
                totalBooks,
                loansToday,
                lastSixMonths,
                mostBorrowedBook,
                mostActiveMember
        );
    }

    private List<MonthlyLoans> buildLastSixMonths(List<Loan> allLoans, LocalDate today) {
        List<MonthlyLoans> result = new ArrayList<>();

        for (int i = 5; i >= 0; i--) {
            LocalDate ref = today.minusMonths(i);
            Month month = ref.getMonth();
            int year = ref.getYear();

            long count = allLoans.stream()
                    .filter(l -> l.getLoanDate().getMonth() == month
                            && l.getLoanDate().getYear() == year)
                    .count();

            result.add(new MonthlyLoans(month.name(), year, count));
        }

        return result;
    }
}