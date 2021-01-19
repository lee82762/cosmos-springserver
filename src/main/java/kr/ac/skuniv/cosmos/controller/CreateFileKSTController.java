package kr.ac.skuniv.cosmos.controller;

import kr.ac.skuniv.cosmos.domain.entity.KSTProject;
import kr.ac.skuniv.cosmos.service.CreateFileKSTService;
import org.springframework.web.bind.annotation.*;

/**
 * KST 파일 생성
 */
@RestController
@RequestMapping("/cosmos/kStars")
public class CreateFileKSTController {

    private final CreateFileKSTService createFileKSTService;

    public CreateFileKSTController(CreateFileKSTService createFileKSTService) {
        this.createFileKSTService = createFileKSTService;
    }

    @PostMapping("/create/kst")
    public void createKSTGuestController(@RequestBody KSTProject kstProject) {
        System.out.println(kstProject.getM_KTierVer2());
        createFileKSTService.createKSTFile(kstProject);
    }

    @PostMapping("/create/kst/user")
    public void createKSTUserController(@RequestHeader("token") String token, @RequestBody KSTProject kstProject) {
        System.out.println(kstProject.getM_KTierVer2());
        createFileKSTService.createKSTUserFile(token, kstProject);
    }
}
