package kr.ac.skuniv.cosmos.service.member;

import kr.ac.skuniv.cosmos.domain.dto.member.MemberSignInDto;
import kr.ac.skuniv.cosmos.domain.entity.member.Member;
import kr.ac.skuniv.cosmos.exception.MemberNotFoundException;
import kr.ac.skuniv.cosmos.exception.WrongPasswordException;
import kr.ac.skuniv.cosmos.jwt.JwtService;
import kr.ac.skuniv.cosmos.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberSignInService {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public String signIn(MemberSignInDto memberSignInDto) {
        Member member = memberRepository.findByEmail(memberSignInDto.getEmail()).orElseThrow(MemberNotFoundException::new);
        checkPw(memberSignInDto, member);
        return jwtService.createJwt(member.getEmail());
    }

    private void checkPw(MemberSignInDto memberSignInDto, Member member) {
        if(passwordEncoder.matches(memberSignInDto.getPassword(), member.getPassword()))
            return;
        throw new WrongPasswordException();
    }

}
