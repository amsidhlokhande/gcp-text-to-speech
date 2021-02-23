package com.amsidh.mvc.speech;

import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileOutputStream;
import java.io.OutputStream;

@SpringBootApplication
@AllArgsConstructor
public class GcpTextToSpeechApplication {

    public static void main(String[] args) {
        SpringApplication.run(GcpTextToSpeechApplication.class, args);
    }

    private final TextToSpeechClient textToSpeechClient;

    public void run(String... args) throws Exception {
        String text = "Hello World! How are you doing today? This is Google Cloud Text-to-Speech Demo!";
        String outputAudioFilePath = "output.mp3";

        // Set the text input to be synthesized
        SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

        // Build the voice request; languageCode = "en_us"
        VoiceSelectionParams voice = VoiceSelectionParams.newBuilder().setLanguageCode("en-US")
                .setSsmlGender(SsmlVoiceGender.FEMALE)
                .build();

        // Select the type of audio file you want returned
        AudioConfig audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3) // MP3 audio.
                .build();

        // Perform the text-to-speech request
        SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

        // Get the audio contents from the response
        ByteString audioContents = response.getAudioContent();

        // Write the response to the output file.
        try (OutputStream out = new FileOutputStream(outputAudioFilePath)) {
            out.write(audioContents.toByteArray());
            System.out.println("Audio content written to file \"output.mp3\"");
        }


    }
}
