package com.amsidh.mvc.speech.service;

import com.amsidh.mvc.speech.controller.speechtotext.Message;
import com.google.protobuf.ByteString;

public interface SpeechToTextService {
    Message convertSpeechToText(ByteString byteString) throws Exception;
}
