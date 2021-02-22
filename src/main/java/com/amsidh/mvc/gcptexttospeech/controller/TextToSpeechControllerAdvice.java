package com.amsidh.mvc.gcptexttospeech.controller;

import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;
import java.util.Date;

@ControllerAdvice
public class TextToSpeechControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleCustomException(Exception exception, WebRequest request) {
        MyCustomException myCustomException = new MyCustomException(new ExceptionModel(exception.getLocalizedMessage(), new Date(), exception));
        return handleExceptionInternal(myCustomException, myCustomException.getExceptionModel(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}


@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
class MyCustomException extends Exception {
    private final ExceptionModel exceptionModel;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class ExceptionModel implements Serializable {
    private String errorMessage;
    private Date timestamp;
    private Exception exception;
}