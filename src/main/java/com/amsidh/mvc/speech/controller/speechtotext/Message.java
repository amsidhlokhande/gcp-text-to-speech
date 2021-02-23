package com.amsidh.mvc.speech.controller.speechtotext;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Message implements Serializable {
    private String contents;
}
