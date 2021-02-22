package com.amsidh.mvc.gcptexttospeech.config;

import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfig {

    @Bean
    public TextToSpeechClient getTextToSpeechClient() throws IOException {
        return TextToSpeechClient.create();
    }
}
