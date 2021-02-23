package com.amsidh.mvc.speech.service.impl;

import com.amsidh.mvc.speech.service.TextToSpeechService;
import com.google.cloud.texttospeech.v1.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Slf4j
@Service
public class TextToSpeechServiceImpl implements TextToSpeechService {

    private final TextToSpeechClient textToSpeechClient;
    private final VoiceSelectionParams voiceSelectionParams;
    private final AudioConfig audioConfig;

    @Override
    public byte[] convertTextToSpeech(String message) {
        log.info("Inside convertTextToSpeech method of TextToSpeechServiceImpl class");
        SynthesisInput synthesisText = getSynthesisText(message);
        log.info("input value got Synthesised");
        // Perform the text-to-speech request
        SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(synthesisText, voiceSelectionParams, audioConfig);
        log.info("Response received from TextToSpeechClient");
        return response.getAudioContent().toByteArray();
    }

    private SynthesisInput getSynthesisText(String message) {
        // Set the text input to be synthesized
        return SynthesisInput.newBuilder().setText(message).build();
    }

}
