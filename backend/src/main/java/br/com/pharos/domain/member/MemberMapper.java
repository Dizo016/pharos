package br.com.pharos.domain.member;

public class MemberMapper {

    public static Member toEntity(MemberRequest request) {
        return Member.builder()
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .notes(request.notes())
                .build();
    }

    public static MemberResponse toResponse(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.getNotes(),
                member.getActive()
        );
    }
}