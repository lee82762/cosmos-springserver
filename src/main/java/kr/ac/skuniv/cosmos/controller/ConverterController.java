package kr.ac.skuniv.cosmos.controller;

import kr.ac.skuniv.cosmos.service.ConverterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * mp3파일 다른 형태로 바꾸기 flac / mp3
 */
@RestController
@RequestMapping("/cosmos/kStars")
public class ConverterController {

    private final ConverterService converterService;

    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping("convert")
    public void converterAudioFile() throws IOException {
        String inputPath, outputPath;
        inputPath = "C:\\Users\\User\\Desktop\\GoogleAPI\\happy.mp3";
        outputPath = "C:\\Users\\User\\Desktop\\GoogleAPI\\writeTest.mp3";
        converterService.ConvertFileToAIFF(inputPath, outputPath);
    }

    @GetMapping("read/file")
    public void readFile() {
        converterService.readFile();
    }
}
