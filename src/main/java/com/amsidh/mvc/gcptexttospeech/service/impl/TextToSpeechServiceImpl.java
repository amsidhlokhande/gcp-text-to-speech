package com.amsidh.mvc.gcptexttospeech.service.impl;

import com.amsidh.mvc.gcptexttospeech.service.TextToSpeechService;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
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

    @Override
    public byte[] convertTextToSpeech(String message) {
        SynthesisInput synthesisText = getSynthesisText(message);
        // Perform the text-to-speech request
        SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(synthesisText, VOICE_SELECTION_PARAMS, AUDIO_CONFIG);
        return response.getAudioContent().toByteArray();
    }

    private SynthesisInput getSynthesisText(String message) {
        // Set the text input to be synthesized
        return SynthesisInput.newBuilder().setText(message).build();
    }

}