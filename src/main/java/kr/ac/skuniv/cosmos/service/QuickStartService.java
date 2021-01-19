package kr.ac.skuniv.cosmos.service;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class QuickStartService {

    public void SpeechToText(int fileNumber) throws IOException {
        try (SpeechClient speechClient = SpeechClient.create()) {

            String fileName = "";
            RecognitionConfig.AudioEncoding audioEncoding = null;
            RecognitionConfig config;
            int hertz = 0;
            // The path to the audio file to transcribe
            if(fileNumber == 1) {
                fileName = "C:\\Users\\User\\Desktop\\GoogleAPI\\happyWav.WAV";
                audioEncoding = RecognitionConfig.AudioEncoding.LINEAR16;
                hertz = 96000;
            }
            else if(fileNumber == 2) {
                fileName = "C:\\Users\\User\\Desktop\\GoogleAPI\\happyFlac.FLAC";
                audioEncoding = RecognitionConfig.AudioEncoding.FLAC;
                hertz = 48000;
            }
            else if(fileNumber == 3) {
                fileName = "C:\\Users\\User\\Desktop\\GoogleAPI\\testWav.WAV";
                audioEncoding = RecognitionConfig.AudioEncoding.LINEAR16;
                hertz = 44100;
            }
            else if(fileNumber == 4) {
                fileName = "C:\\Users\\User\\Desktop\\GoogleAPI\\happyFlacMono.FLAC";
                audioEncoding = RecognitionConfig.AudioEncoding.FLAC;
                hertz = 48000;
            }

            System.out.println("분석시작");

            // Reads the audio file into memory
            Path path = Paths.get(fileName);
            byte[] data = Files.readAllBytes(path);
            ByteString audioBytes = ByteString.copyFrom(data);

            System.out.println("PATH : " + path);
            System.out.println("Data : " + data.toString());
            System.out.println("AudioByte : " + audioBytes.toString());

            // Builds the sync recognize request
            if(fileNumber == 3 || fileNumber == 4) {
                config = RecognitionConfig.newBuilder()
                        .setEncoding(audioEncoding)
                        .setSampleRateHertz(hertz)
                        .setLanguageCode("ko-KR")
                        .build();
            }
            else {
                config = RecognitionConfig.newBuilder()
                        .setEncoding(audioEncoding)
                        .setSampleRateHertz(hertz)
                        .setAudioChannelCount(2)
                        .setEnableSeparateRecognitionPerChannel(true)
                        .setLanguageCode("ko-KR")
                        .build();
            }

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(audioBytes)
                    .build();

            System.out.println("Config : " + config.toString());
            System.out.println("Audio : " + audio.toString());

            // Performs speech recognition on the audio file
            RecognizeResponse response = speechClient.recognize(config, audio);

            System.out.println("Response : " + response);

            List<SpeechRecognitionResult> results = response.getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s%n", alternative.getTranscript());
            }
        }
    }

//    public void authImplicit() {
//        // If you don't specify credentials when constructing the client, the client library will
//        // look for credentials via the environment variable GOOGLE_APPLICATION_CREDENTIALS.
//        Storage storage = StorageOptions.getDefaultInstance().getService();
//
//        System.out.println("Buckets:");
//        Page<Bucket> buckets = storage.list();
//        for (Bucket bucket : buckets.iterateAll()) {
//            System.out.println(bucket.toString());
//        }
//    }
//
//    public void authExplicit(String jsonPath) throws IOException {
//        // You can specify a credential file by providing a path to GoogleCredentials.
//        // Otherwise credentials are read from the GOOGLE_APPLICATION_CREDENTIALS environment variable.
//        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
//                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
//        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
//
//        System.out.println("Buckets:");
//        Page<Bucket> buckets = storage.list();
//        for (Bucket bucket : buckets.iterateAll()) {
//            System.out.println(bucket.toString());
//        }
//    }
}

