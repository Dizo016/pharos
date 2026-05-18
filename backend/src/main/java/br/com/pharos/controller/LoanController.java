package br.com.pharos.controller;

import br.com.pharos.domain.loan.LoanRequest;
import br.com.pharos.domain.loan.LoanResponse;
import br.com.pharos.domain.loan.LoanStatus;
import br.com.pharos.service.loan.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponse> create(@RequestBody @Valid LoanRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<LoanResponse>> findAll() {
        return ResponseEntity.ok(loanService.findAll());
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<LoanResponse>> findOverdue() {
        return ResponseEntity.ok(loanService.findOverdue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(loanService.findById(id));
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<LoanResponse>> findByMember(@PathVariable UUID memberId) {
        return ResponseEntity.ok(loanService.findByMember(memberId));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<LoanResponse>> findByBook(@PathVariable UUID bookId) {
        return ResponseEntity.ok(loanService.findByBook(bookId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LoanResponse>> findByStatus(@PathVariable LoanStatus status) {
        return ResponseEntity.ok(loanService.findByStatus(status));
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<LoanResponse> returnBook(@PathVariable UUID id) {
        return ResponseEntity.ok(loanService.returnBook(id));
    }

    @PatchMapping("/{id}/renew")
    public ResponseEntity<LoanResponse> renew(
            @PathVariable UUID id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newDueDate) {
        return ResponseEntity.ok(loanService.renew(id, newDueDate));
    }
}