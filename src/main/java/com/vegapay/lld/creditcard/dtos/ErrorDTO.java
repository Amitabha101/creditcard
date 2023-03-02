package com.vegapay.lld.creditcard.dtos;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ErrorDTO {

    private final int status;
    private final String error;
    private final String message;
    private List<String> detailedMessages;

    public ErrorDTO(HttpStatus httpStatus, String message) {
        status = httpStatus.value();
        error = httpStatus.getReasonPhrase();
        this.message = message;
    }

}