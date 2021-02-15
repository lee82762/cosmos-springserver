package kr.ac.skuniv.cosmos.controller.member;

import kr.ac.skuniv.cosmos.domain.dto.member.MemberCreateDto;
import kr.ac.skuniv.cosmos.domain.dto.member.MemberGetDto;
import kr.ac.skuniv.cosmos.domain.dto.member.MemberSignInDto;
import kr.ac.skuniv.cosmos.service.member.MemberCreateService;
import kr.ac.skuniv.cosmos.service.member.MemberSignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
//멤버 관리
@RestController
@CrossOrigin
@RequestMapping("/cosmos/kStars")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCreateService memberCreateService;
    private final MemberSignInService memberSignInService;


    @PostMapping(value = "/signUp")
    public ResponseEntity createMember(@RequestBody MemberCreateDto memberCreateDto){
        MemberGetDto savedMember = memberCreateService.createMember(memberCreateDto);
        return ResponseEntity.created(URI.create("/sign/" + savedMember.getId())).body(savedMember);
    }

    @PostMapping("/signIn")
    public String signIn(@RequestBody MemberSignInDto memberSignInDto){
        System.out.println(memberSignInDto.getEmail() + " : " + memberSignInDto.getPassword());
        return memberSignInService.signIn(memberSignInDto);
    }
}
