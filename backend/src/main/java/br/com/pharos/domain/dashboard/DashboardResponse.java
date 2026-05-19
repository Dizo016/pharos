package br.com.pharos.domain.dashboard;

import java.util.List;

public record DashboardResponse(
        long totalMembers,
        long activeLoans,
        long overdueLoans,
        long availableBooks,
        long totalBooks,
        long loansToday,
        List<MonthlyLoans> lastSixMonths,
        String mostBorrowedBook,
        String mostActiveMembers
) {
    public record MonthlyLoans(String month, int year, long count) {}
}