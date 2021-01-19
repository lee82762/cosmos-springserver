package kr.ac.skuniv.cosmos.service;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import kr.ac.skuniv.cosmos.domain.dto.ConvertDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FlacConvertService {

    @Autowired
    RestTemplate restTemplate;

    public void convertToFlac(ConvertDto convertDto) {

//        ConvertDto file = restTemplate.postForObject("http://localhost:5000/convert", convertDto, ConvertDto.class);
        ConvertDto file = restTemplate.postForObject("http://18.221.109.14:5000/convert", convertDto, ConvertDto.class);

        String fileName = file.getFilePath();

        System.out.println(file.getFilePath());

        try (SpeechClient speechClient = SpeechClient.create()) {

            RecognitionConfig.AudioEncoding audioEncoding = RecognitionConfig.AudioEncoding.FLAC;
            RecognitionConfig config;

            Path path = Paths.get(fileName);
            byte[] data = Files.readAllBytes(path);
            ByteString audioBytes = ByteString.copyFrom(data);

            config = RecognitionConfig.newBuilder()
                    .setEncoding(audioEncoding)
                    .setSampleRateHertz(48000)
                    .setAudioChannelCount(2)
                    .setLanguageCode("ko-KR")
                    .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(audioBytes)
                    .build();

            RecognizeResponse response = speechClient.recognize(config, audio);

            System.out.println("Response : " + response);

            List<SpeechRecognitionResult> results = response.getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s%n", alternative.getTranscript());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
