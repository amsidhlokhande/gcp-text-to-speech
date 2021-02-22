package com.amsidh.mvc.gcptexttospeech.controller;

import com.amsidh.mvc.gcptexttospeech.service.TextToSpeechService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Data
@AllArgsConstructor
@Slf4j
@RestController
public class TextToSpeechController {

    private final TextToSpeechService textToSpeechService;

    @PostMapping(value = "/textToSpeechFile", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> textToAudioFile(@RequestBody String text) throws IOException {
        byte[] bytes = textToSpeechService.convertTextToSpeech(text);
        return ResponseEntity.ok()
                .contentLength(bytes.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "output.mp3")
                .body(bytes);
    }


    @GetMapping(value = "/textToSpeechFile", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> textInPathToAudioFile(@RequestParam(value = "text", required = true) String text) throws IOException {
        byte[] bytes = textToSpeechService.convertTextToSpeech(text);
        return ResponseEntity.ok()
                .contentLength(bytes.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "output.mp3")
                .body(bytes);
    }

}
