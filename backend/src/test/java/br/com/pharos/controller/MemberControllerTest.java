package br.com.pharos.controller;

import br.com.pharos.domain.member.Member;
import br.com.pharos.repository.member.MemberRepository;
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
class MemberControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    private MockMvc mockMvc;
    private Member savedMember;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        savedMember = memberRepository.save(Member.builder()
                .name("Ana Lima")
                .email("ana@email.com")
                .phone("71999990000")
                .build());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateMember() throws Exception {
        String body = """
                {
                    "name": "Carlos Souza",
                    "email": "carlos@email.com",
                    "phone": "71988880000"
                }
                """;

        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Carlos Souza"))
                .andExpect(jsonPath("$.email").value("carlos@email.com"))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnConflictWhenEmailAlreadyExists() throws Exception {
        String body = """
                {
                    "name": "Outro Nome",
                    "email": "ana@email.com"
                }
                """;

        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnBadRequestWhenNameIsBlank() throws Exception {
        String body = """
                {
                    "name": "",
                    "email": "novo@email.com"
                }
                """;

        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.name").isNotEmpty());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnBadRequestWhenEmailIsInvalid() throws Exception {
        String body = """
                {
                    "name": "Teste",
                    "email": "email-invalido"
                }
                """;

        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.email").isNotEmpty());
    }

    @Test
    @WithMockUser(roles = "LIBRARIAN")
    void shouldListOnlyActiveMembers() throws Exception {
        memberRepository.save(Member.builder()
                .name("Inativo")
                .email("inativo@email.com")
                .active(false)
                .build());

        mockMvc.perform(get("/members"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Ana Lima"));
    }

    @Test
    @WithMockUser(roles = "LIBRARIAN")
    void shouldFindMemberById() throws Exception {
        mockMvc.perform(get("/members/{id}", savedMember.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedMember.getId().toString()))
                .andExpect(jsonPath("$.name").value("Ana Lima"));
    }

    @Test
    @WithMockUser(roles = "LIBRARIAN")
    void shouldReturn404WhenMemberNotFound() throws Exception {
        mockMvc.perform(get("/members/{id}", java.util.UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "LIBRARIAN")
    void shouldSearchMemberByName() throws Exception {
        mockMvc.perform(get("/members/search").param("name", "Ana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Ana Lima"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldUpdateMember() throws Exception {
        String body = """
                {
                    "name": "Ana Souza",
                    "email": "ana.souza@email.com",
                    "phone": "71911110000"
                }
                """;

        mockMvc.perform(put("/members/{id}", savedMember.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ana Souza"))
                .andExpect(jsonPath("$.email").value("ana.souza@email.com"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeactivateMember() throws Exception {
        mockMvc.perform(delete("/members/{id}", savedMember.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/members"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void shouldReturn403WhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/members"))
                .andExpect(status().isForbidden());
    }
}