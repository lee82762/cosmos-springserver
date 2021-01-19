package kr.ac.skuniv.cosmos.controller;

import kr.ac.skuniv.cosmos.domain.dto.ConvertDto;
import kr.ac.skuniv.cosmos.service.FlacConvertService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cosmos/kStars")
public class FlacConvertController {

    private final FlacConvertService flacConvertService;

    public FlacConvertController(FlacConvertService flacConvertService) {
        this.flacConvertService = flacConvertService;
    }

    @PostMapping("/convertToFlacAndSpeechToText")
    public void convertToFlac22(@RequestBody ConvertDto convertDto) {
        flacConvertService.convertToFlac(convertDto);
    }

}