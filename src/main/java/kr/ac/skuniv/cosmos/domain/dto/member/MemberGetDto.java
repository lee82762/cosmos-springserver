package kr.ac.skuniv.cosmos.domain.dto.member;

import kr.ac.skuniv.cosmos.domain.entity.member.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberGetDto {

    private Long id;
    private String email;

    @Builder
    public MemberGetDto(Long id,
                        String email) {
        this.id = id;
        this.email = email;
    }



    public static MemberGetDto toDto(Member member){
        return MemberGetDto.builder()
                .email(member.getEmail())
                .id(member.getId())
                .build();
    }
}
