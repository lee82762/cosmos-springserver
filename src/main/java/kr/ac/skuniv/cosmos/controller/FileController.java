package kr.ac.skuniv.cosmos.controller;

import kr.ac.skuniv.cosmos.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/cosmos/kStars")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/file/save")
    public ResponseEntity<String> saveFile(@RequestPart MultipartFile musicFile) throws IOException {

        return ResponseEntity.ok(fileService.saveFile(musicFile));

    }

    @GetMapping(value = "/file/get", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getImage(@RequestPart MultipartFile musicFile) throws IOException {

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileService.getFileResource(fileService.saveFile(musicFile)));
    }



}
