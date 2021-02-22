package com.amsidh.mvc.gcptexttospeech.service;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;

public interface TextToSpeechService {
    // Build the voice request; languageCode = "en_us"
    VoiceSelectionParams VOICE_SELECTION_PARAMS = VoiceSelectionParams.newBuilder().setLanguageCode("en-US")
            .setSsmlGender(SsmlVoiceGender.FEMALE)
            .build();

    // Select the type of audio file you want returned
    AudioConfig AUDIO_CONFIG = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3) // MP3 audio.
            .build();

    byte[] convertTextToSpeech(String message);
}
