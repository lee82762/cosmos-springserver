package kr.ac.skuniv.cosmos.domain.dto.member;

import kr.ac.skuniv.cosmos.domain.entity.member.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCreateDto {

    private String email;
    private String password;

    public Member of(){
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .role("USER")
                .build();
    }
    @Builder
    public MemberCreateDto(String email,
                           String password) {
        this.email = email;
        this.password = password;
    }
}
