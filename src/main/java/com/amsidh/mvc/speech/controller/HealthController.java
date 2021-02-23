package com.amsidh.mvc.speech.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/googlespeech")
public class HealthController {

    private static final String HOST_NAME = "HOSTNAME";
    private static final String DEFAULT_ENV_INSTANCE_GUID = "UNKNOWN";
    @Value("${" + HOST_NAME + ":" + DEFAULT_ENV_INSTANCE_GUID + "}")
    private String hostName;

    @GetMapping("/healthcheck")
    public String healthCheck() {
        log.info("Inside healthCheck Method");
        return "{\"Status\":\"HelloWorld App is up and Running\", \"Hostname\": \"" + hostName + "\"}";
    }

}
