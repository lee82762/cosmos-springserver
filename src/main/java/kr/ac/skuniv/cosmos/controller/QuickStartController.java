package kr.ac.skuniv.cosmos.controller;

import kr.ac.skuniv.cosmos.service.QuickStartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/cosmos/kStars")
public class QuickStartController {

    private final QuickStartService quickStartService;

    public QuickStartController(QuickStartService quickStartService) {
        this.quickStartService = quickStartService;
    }

    @GetMapping("/speech/to/text/{fileNumber}")
    public void SpeechToText(@PathVariable int fileNumber) throws IOException {
        quickStartService.SpeechToText(fileNumber);
    }

//    @GetMapping("/auth1")
//    public void Test() {
//        quickStartService.authImplicit();
//    }
//
//    @GetMapping("/auth20")
//    public void AuthTest() throws IOException {
//        quickStartService.authExplicit("C:\\Users\\User\\CosmosSpeechToText-dc2a0736f5d5.json");
//    }
}
