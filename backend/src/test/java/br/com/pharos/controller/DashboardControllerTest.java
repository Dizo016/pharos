package br.com.pharos.controller;

import br.com.pharos.domain.book.Book;
import br.com.pharos.domain.book.BookCategory;
import br.com.pharos.domain.loan.Loan;
import br.com.pharos.domain.loan.LoanStatus;
import br.com.pharos.domain.member.Member;
import br.com.pharos.repository.book.BookRepository;
import br.com.pharos.repository.loan.LoanRepository;
import br.com.pharos.repository.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class DashboardControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoanRepository loanRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        Member member = memberRepository.save(Member.builder()
                .name("Leitor Teste")
                .email("leitor@email.com")
                .build());

        Book book = bookRepository.save(Book.builder()
                .title("Livro Teste")
                .author("Autor Teste")
                .category(BookCategory.SCIENCE)
                .totalCopies(2)
                .availableCopies(1)
                .build());

        loanRepository.save(Loan.builder()
                .member(member)
                .book(book)
                .loanDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(14))
                .status(LoanStatus.ACTIVE)
                .build());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnDashboardMetrics() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalMembers").value(1))
                .andExpect(jsonPath("$.activeLoans").value(1))
                .andExpect(jsonPath("$.overdueLoans").value(0))
                .andExpect(jsonPath("$.availableBooks").value(1))
                .andExpect(jsonPath("$.totalBooks").value(1))
                .andExpect(jsonPath("$.loansToday").value(1))
                .andExpect(jsonPath("$.lastSixMonths").isArray())
                .andExpect(jsonPath("$.lastSixMonths.length()").value(6))
                .andExpect(jsonPath("$.mostBorrowedBook").value("Livro Teste"));
    }

    @Test
    @WithMockUser(roles = "LIBRARIAN")
    void shouldReturnDashboardForLibrarian() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalMembers").isNumber())
                .andExpect(jsonPath("$.activeLoans").isNumber());
    }

    @Test
    void shouldReturn403WhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().isForbidden());
    }
}