package br.com.pharos.service.member;

import br.com.pharos.domain.member.Member;
import br.com.pharos.domain.member.MemberMapper;
import br.com.pharos.domain.member.MemberRequest;
import br.com.pharos.domain.member.MemberResponse;
import br.com.pharos.repository.member.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    public MemberResponse create(MemberRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already in use");
        }
        return MemberMapper.toResponse(repository.save(MemberMapper.toEntity(request)));
    }

    public List<MemberResponse> findAll() {
        return repository.findByActiveTrue()
                .stream()
                .map(MemberMapper::toResponse)
                .toList();
    }

    public MemberResponse findById(UUID id) {
        return MemberMapper.toResponse(getById(id));
    }

    public List<MemberResponse> findByName(String name) {
        return repository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(MemberMapper::toResponse)
                .toList();
    }

    public MemberResponse update(UUID id, MemberRequest request) {
        Member existing = getById(id);
        existing.setName(request.name());
        existing.setEmail(request.email());
        existing.setPhone(request.phone());
        existing.setNotes(request.notes());
        return MemberMapper.toResponse(repository.save(existing));
    }

    public void deactivate(UUID id) {
        Member member = getById(id);
        member.setActive(false);
        repository.save(member);
    }

    private Member getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
    }
}