package kr.ac.skuniv.cosmos.domain.entity.member;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity(name = "member_tbl")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    private String role;

    @Builder
    public Member(final String email,
                  final String password,
                  final String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
