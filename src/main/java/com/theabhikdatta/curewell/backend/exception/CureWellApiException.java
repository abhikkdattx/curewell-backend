package com.theabhikdatta.curewell.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CureWellApiException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public CureWellApiException(String customMessage, HttpStatus status, String message){
        super(customMessage);
        this.status = status;
        this.message = message;
    }
}
