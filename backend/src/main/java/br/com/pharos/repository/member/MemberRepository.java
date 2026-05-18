package br.com.pharos.repository.member;

import br.com.pharos.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

    List<Member> findByActiveTrue();
    List<Member> findByNameContainingIgnoreCase(String name);
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);

}