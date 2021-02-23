package com.amsidh.mvc.speech.controller.texttospeech;

import com.amsidh.mvc.speech.service.TextToSpeechService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Data
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/googlespeech")
public class TextToSpeechController {

    private final TextToSpeechService textToSpeechService;

    @PostMapping(value = "/texttospeech", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> textToAudioFile(@RequestBody String text) throws IOException {
        log.info("Inside textToAudioFile method");
        byte[] bytes = textToSpeechService.convertTextToSpeech(text);
        return ResponseEntity.ok()
                .contentLength(bytes.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "output.wav")
                .body(bytes);
    }

    @GetMapping(value = "/texttospeech", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> textInPathToAudioFile(@RequestParam(value = "text", required = true) String text) throws IOException {
        log.info("Inside textInPathToAudioFile method");
        byte[] bytes = textToSpeechService.convertTextToSpeech(text);
        return ResponseEntity.ok()
                .contentLength(bytes.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "output.wav")
                .body(bytes);
    }

}
