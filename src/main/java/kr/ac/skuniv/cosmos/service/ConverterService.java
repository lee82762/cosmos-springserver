package kr.ac.skuniv.cosmos.service;

import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

@Service
public class ConverterService {

    public void ConvertFileToAIFF(String inputPath, String outputPath) {

        AudioFileFormat inFileFormat;
        File inFile;
        File outFile;
        try {
            inFile = new File(inputPath);
            outFile = new File(outputPath);
        } catch (NullPointerException ex) {
            System.out.println("Error: one of the ConvertFileToAIFF" +" parameters is null!");
            return;
        }
        try {
            // query file type
            inFileFormat = AudioSystem.getAudioFileFormat(inFile);
            if (inFileFormat.getType() != AudioFileFormat.Type.AIFF)
            {
                // inFile is not AIFF, so let's try to convert it.
                AudioInputStream inFileAIS =
                        AudioSystem.getAudioInputStream(inFile);
                inFileAIS.reset(); // rewind
                if (AudioSystem.isFileTypeSupported(
                        AudioFileFormat.Type.AIFF, inFileAIS)) {
                    // inFileAIS can be converted to AIFF.
                    // so write the AudioInputStream to the
                    // output file.
                    AudioSystem.write(inFileAIS,
                            AudioFileFormat.Type.AIFF, outFile);
                    System.out.println("Successfully made AIFF file, "
                            + outFile.getPath() + ", from "
                            + inFileFormat.getType() + " file, " +
                            inFile.getPath() + ".");
                    inFileAIS.close();
                    return; // All done now
                } else
                    System.out.println("Warning: AIFF conversion of "
                            + inFile.getPath()
                            + " is not currently supported by AudioSystem.");
            } else
                System.out.println("Input file " + inFile.getPath() +
                        " is AIFF." + " Conversion is unnecessary.");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Error: " + inFile.getPath()
                    + " is not a supported audio file type!");
            return;
        } catch (IOException e) {
            System.out.println("Error: failure attempting to read "
                    + inFile.getPath() + "!");
            return;
        }
    }

    public void readFile() {

        int totalFramesRead = 0;
        String somePathName = "C:\\Users\\User\\Desktop\\GoogleAPI\\testWav.WAV";
        File fileIn = new File(somePathName);
        // somePathName is a pre-existing string whose value was
        // based on a user selection.
        System.out.println("시작");
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(fileIn);

            System.out.println("AudioInputStream : " + audioInputStream);

            int bytesPerFrame =
                    audioInputStream.getFormat().getFrameSize();
            if (bytesPerFrame == AudioSystem.NOT_SPECIFIED) {
                // some audio formats may have unspecified frame size
                // in that case we may read any amount of bytes
                bytesPerFrame = 1;
            }
            // Set an arbitrary buffer size of 1024 frames.
            int numBytes = 1024 * bytesPerFrame;
            byte[] audioBytes = new byte[numBytes];
            try {
                int numBytesRead = 0;
                int numFramesRead = 0;
                // Try to read numBytes bytes from the file.
                while ((numBytesRead =
                        audioInputStream.read(audioBytes)) != -1) {
                    // Calculate the number of frames actually read.
                    numFramesRead = numBytesRead / bytesPerFrame;
                    totalFramesRead += numFramesRead;
                    // Here, do something useful with the audio data that's
                    // now in the audioBytes array...
                }
            } catch (Exception ex) {
                // Handle the error...
            }
        } catch (Exception e) {
            // Handle the error...
            System.out.println("Not Support Sound API");
        }
    }
}

