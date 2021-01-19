package kr.ac.skuniv.cosmos.domain.dto.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSignInDto {

    private String email;
    private String password;

    @Builder
    public MemberSignInDto(String email,
                           String password) {
        this.email = email;
        this.password = password;
    }
}
