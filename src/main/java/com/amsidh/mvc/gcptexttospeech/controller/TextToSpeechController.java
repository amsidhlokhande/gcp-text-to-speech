package com.amsidh.mvc.gcptexttospeech.controller;

import com.amsidh.mvc.gcptexttospeech.service.TextToSpeechService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Data
@Slf4j
@RestController
public class TextToSpeechController {

    @Autowired
    private TextToSpeechService textToSpeechService;

    private static final String HOST_NAME = "HOSTNAME";
    private static final String DEFAULT_ENV_INSTANCE_GUID = "UNKNOWN";
    @Value("${" + HOST_NAME + ":" + DEFAULT_ENV_INSTANCE_GUID + "}")
    private String hostName;

    @GetMapping("/healthCheck")
    public String healthCheck() {
        log.info("Inside healthCheck Method");
        return "{\"Status\":\"HelloWorld App is up and Running\", \"hostname\": \"" + hostName + "\"}";
    }

    @PostMapping(value = "/textToSpeechFile", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> textToAudioFile(@RequestBody String text) throws IOException {
        log.info("Inside textToAudioFile method");
        byte[] bytes = textToSpeechService.convertTextToSpeech(text);
        return ResponseEntity.ok()
                .contentLength(bytes.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "output.mp3")
                .body(bytes);
    }


    @GetMapping(value = "/textToSpeechFile", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> textInPathToAudioFile(@RequestParam(value = "text", required = true) String text) throws IOException {
        log.info("Inside textInPathToAudioFile method");
        byte[] bytes = textToSpeechService.convertTextToSpeech(text);
        return ResponseEntity.ok()
                .contentLength(bytes.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "output.mp3")
                .body(bytes);
    }

}
