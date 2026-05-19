package br.com.pharos.controller;

import br.com.pharos.domain.book.Book;
import br.com.pharos.domain.book.BookCategory;
import br.com.pharos.repository.book.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class BookControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BookRepository bookRepository;

    private MockMvc mockMvc;
    private Book savedBook;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        savedBook = bookRepository.save(Book.builder()
                .title("O Senhor dos Anéis")
                .author("J.R.R. Tolkien")
                .isbn("978-0-261-10235-4")
                .category(BookCategory.FICTION)
                .totalCopies(3)
                .availableCopies(3)
                .build());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateBook() throws Exception {
        String body = """
                {
                    "title": "1984",
                    "author": "George Orwell",
                    "isbn": "978-0-451-52493-5",
                    "category": "FICTION",
                    "totalCopies": 5
                }
                """;

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.title").value("1984"))
                .andExpect(jsonPath("$.author").value("George Orwell"))
                .andExpect(jsonPath("$.totalCopies").value(5))
                .andExpect(jsonPath("$.availableCopies").value(5))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnConflictWhenIsbnAlreadyExists() throws Exception {
        String body = """
                {
                    "title": "Outro Livro",
                    "author": "Outro Autor",
                    "isbn": "978-0-261-10235-4",
                    "category": "FICTION",
                    "totalCopies": 1
                }
                """;

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnBadRequestWhenTitleIsBlank() throws Exception {
        String body = """
                {
                    "title": "",
                    "author": "Autor",
                    "category": "FICTION",
                    "totalCopies": 1
                }
                """;

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.title").isNotEmpty());
    }

    @Test
    @WithMockUser(roles = "LIBRARIAN")
    void shouldListOnlyActiveBooks() throws Exception {
        bookRepository.save(Book.builder()
                .title("Livro Inativo")
                .author("Autor")
                .category(BookCategory.OTHER)
                .totalCopies(1)
                .availableCopies(1)
                .active(false)
                .build());

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("O Senhor dos Anéis"));
    }

    @Test
    @WithMockUser(roles = "LIBRARIAN")
    void shouldFindBookById() throws Exception {
        mockMvc.perform(get("/books/{id}", savedBook.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedBook.getId().toString()))
                .andExpect(jsonPath("$.title").value("O Senhor dos Anéis"));
    }

    @Test
    @WithMockUser(roles = "LIBRARIAN")
    void shouldReturn404WhenBookNotFound() throws Exception {
        mockMvc.perform(get("/books/{id}", java.util.UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "LIBRARIAN")
    void shouldSearchBookByTitle() throws Exception {
        mockMvc.perform(get("/books/search/title").param("title", "Senhor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("O Senhor dos Anéis"));
    }

    @Test
    @WithMockUser(roles = "LIBRARIAN")
    void shouldSearchBookByAuthor() throws Exception {
        mockMvc.perform(get("/books/search/author").param("author", "Tolkien"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].author").value("J.R.R. Tolkien"));
    }

    @Test
    @WithMockUser(roles = "LIBRARIAN")
    void shouldFindAvailableBooks() throws Exception {
        bookRepository.save(Book.builder()
                .title("Esgotado")
                .author("Autor")
                .category(BookCategory.OTHER)
                .totalCopies(2)
                .availableCopies(0)
                .build());

        mockMvc.perform(get("/books/available"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("O Senhor dos Anéis"));
    }

    @Test
    @WithMockUser(roles = "LIBRARIAN")
    void shouldFindBooksByCategory() throws Exception {
        mockMvc.perform(get("/books/category/{category}", BookCategory.FICTION))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category").value("FICTION"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldUpdateBook() throws Exception {
        String body = """
                {
                    "title": "O Senhor dos Anéis - Edição Revisada",
                    "author": "J.R.R. Tolkien",
                    "category": "FICTION",
                    "totalCopies": 5
                }
                """;

        mockMvc.perform(put("/books/{id}", savedBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("O Senhor dos Anéis - Edição Revisada"))
                .andExpect(jsonPath("$.totalCopies").value(5))
                .andExpect(jsonPath("$.availableCopies").value(5));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeactivateBook() throws Exception {
        mockMvc.perform(delete("/books/{id}", savedBook.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void shouldReturn403WhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isForbidden());
    }
}