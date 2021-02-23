package com.amsidh.mvc.speech.service.impl;

import com.amsidh.mvc.speech.controller.speechtotext.Message;
import com.amsidh.mvc.speech.service.SpeechToTextService;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

@AllArgsConstructor
@Slf4j
@Service
public class SpeechToTextServiceImpl implements SpeechToTextService {

    private final SpeechClient speechClient;
    private final RecognitionConfig recognitionConfig;
    private final RecognitionAudio.Builder recognitionAudioBuilder;

    @Override
    public Message convertSpeechToText(ByteString byteString) throws Exception {
        log.info("Inside convertSpeechToText method of SpeechToTextServiceImpl");
        RecognitionAudio recognitionAudio = recognitionAudioBuilder.setContent(byteString).build();

        log.info("Calling google speech to voice API");
        OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response = speechClient.longRunningRecognizeAsync(recognitionConfig, recognitionAudio);

        while (!response.isDone()) {
            log.info("Waiting for response from google speech to voice API");
            sleep(10000);
        }

        log.info("Response received from google speech to voice API");
        StringBuilder stringBuilder = new StringBuilder();
        response.get().getResultsList().forEach((SpeechRecognitionResult speechRecognitionResult) -> stringBuilder.append(speechRecognitionResult.getAlternativesList().get(0).getTranscript()));
        log.info("Response parsed and send to caller of this SpeechToTextServiceImpl service");
        return new Message(stringBuilder.toString());
    }
}
