package com.amsidh.mvc.gcptexttospeech.config;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.TextToSpeechSettings;
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

    @Bean
    public TextToSpeechClient getTextToSpeechClient() throws IOException {
        log.info("Key file path "+ keyFilePath);
        CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(ServiceAccountCredentials.fromStream(new FileInputStream(keyFilePath)));
        TextToSpeechSettings settings = TextToSpeechSettings.newBuilder().setCredentialsProvider(credentialsProvider).build();
        TextToSpeechClient textToSpeechClient = TextToSpeechClient.create(settings);

        return textToSpeechClient;

    }
}
