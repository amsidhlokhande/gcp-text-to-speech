package com.amsidh.mvc.speech.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OfferVO {

    public String region;
    public String gender;
    public Double balance;
    public Integer age;

}
