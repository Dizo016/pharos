package br.com.pharos.controller;

import br.com.pharos.domain.member.MemberRequest;
import br.com.pharos.domain.member.MemberResponse;
import br.com.pharos.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse> create(@RequestBody @Valid MemberRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MemberResponse>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(memberService.findByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid MemberRequest request) {
        return ResponseEntity.ok(memberService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable UUID id) {
        memberService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}