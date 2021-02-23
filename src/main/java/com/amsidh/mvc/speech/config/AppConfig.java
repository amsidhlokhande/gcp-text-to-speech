package com.amsidh.mvc.speech.config;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechSettings;
import com.google.cloud.texttospeech.v1.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@Slf4j
public class AppConfig {

    @Value("${GOOGLE_APPLICATION_CREDENTIALS:D:\\Google\\TextToSpeech\\amsidh-microservice-project-505af11eaa22.json}")
    private String keyFilePath;

/*
 ========================================== Common Bean used for both TextToSpeech And SpeechToText ==================================================================
*/

    @Bean
    public CredentialsProvider getCredentialsProvider() throws IOException {
        log.info("Key file path " + keyFilePath);
        return FixedCredentialsProvider.create(ServiceAccountCredentials.fromStream(new FileInputStream(keyFilePath)));

    }

/*
 ========================================== Below beans are used for Text To Speech Google API ==================================================================
*/

    @Bean
    public TextToSpeechClient getTextToSpeechClient() throws IOException {
        TextToSpeechSettings settings = TextToSpeechSettings.newBuilder().setCredentialsProvider(getCredentialsProvider()).build();
        TextToSpeechClient textToSpeechClient = TextToSpeechClient.create(settings);

        return textToSpeechClient;
    }

    @Bean
    public VoiceSelectionParams getVoiceSelectionParams(){
        return VoiceSelectionParams.newBuilder().setLanguageCode("en-US")
                .setSsmlGender(SsmlVoiceGender.FEMALE)
                .build();
    }

    @Bean
    public AudioConfig getAudioConfig(){
        return AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.LINEAR16)
                .build();
    }

/*
 ========================================== Below beans are used for Speech to Text Google API ==================================================================
*/

    @Bean
    public SpeechClient getSpeechClient() throws IOException {
        return SpeechClient.create(getSpeechSettings());
    }

    @Bean
    public SpeechSettings getSpeechSettings() throws IOException {
        return SpeechSettings.newBuilder().setCredentialsProvider(getCredentialsProvider()).build();
    }

    @Bean
    public RecognitionConfig getRecognitionConfig() {
        return RecognitionConfig.newBuilder()
                .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                .setLanguageCode("en-US").setEnableAutomaticPunctuation(true)
                .setEnableWordTimeOffsets(true)
                //.setSampleRateHertz(8000)
                .setModel("default").build();
    }

    @Bean
    public RecognitionAudio.Builder getRecognitionAudio() {
        return RecognitionAudio.newBuilder();
    }
}
