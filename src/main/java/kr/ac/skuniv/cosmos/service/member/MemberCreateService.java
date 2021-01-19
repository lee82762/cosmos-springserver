package kr.ac.skuniv.cosmos.service.member;

import kr.ac.skuniv.cosmos.domain.dto.member.MemberCreateDto;
import kr.ac.skuniv.cosmos.domain.dto.member.MemberGetDto;
import kr.ac.skuniv.cosmos.domain.entity.member.Member;
import kr.ac.skuniv.cosmos.exception.DuplicatedEmailException;
import kr.ac.skuniv.cosmos.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCreateService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberGetDto createMember(MemberCreateDto memberCreateDto) {
        if(memberRepository.existsByEmail(memberCreateDto.getEmail()))
            throw new DuplicatedEmailException();

        memberCreateDto.setPassword(passwordEncoder.encode(memberCreateDto.getPassword()));

        Member savedMember = memberRepository.save(memberCreateDto.of());

        return MemberGetDto.toDto(savedMember);
    }

}
