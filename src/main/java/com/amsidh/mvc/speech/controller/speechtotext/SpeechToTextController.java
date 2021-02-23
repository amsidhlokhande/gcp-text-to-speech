package com.amsidh.mvc.speech.controller.speechtotext;

import com.amsidh.mvc.speech.service.SpeechToTextService;
import com.google.protobuf.ByteString;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/googlespeech")
public class SpeechToTextController {

    private final SpeechToTextService speechToTextService;

    @PostMapping(value = "/speechtotext", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message speechToText(@RequestParam("file") MultipartFile file) throws Exception {
        ByteString byteString = ByteString.copyFrom(file.getBytes());
        return speechToTextService.convertSpeechToText(byteString);
    }
}

