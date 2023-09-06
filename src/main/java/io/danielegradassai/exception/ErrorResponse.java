package io.danielegradassai.exception;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private Date data = new Date();
    private int codice;
    private String messaggio;

}
