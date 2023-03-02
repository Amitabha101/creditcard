package com.vegapay.lld.creditcard.exeption;

import com.vegapay.lld.creditcard.dtos.ErrorDTO;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.stream.Collectors;

@ControllerAdvice
public class CentralExeptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {

        ErrorDTO dto = new ErrorDTO(HttpStatus.BAD_REQUEST, "Validation error");

        dto.setDetailedMessages(exception.getBindingResult().getAllErrors().stream()
                .map(err -> err.unwrap(ConstraintViolation.class))
                .map(err -> String.format("'%s' %s", err.getPropertyPath(), err.getMessage()))
                .collect(Collectors.toList()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        ErrorDTO dto = new ErrorDTO(HttpStatus.BAD_REQUEST, "Invalid request body");

        dto.setDetailedMessages(Collections.singletonList(ex.getMessage()));

        return ResponseEntity.badRequest().body(dto);
    }


    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ErrorDTO> InvalidArgumentExceptionHandler(InvalidArgumentException exception) {
        ErrorDTO dto = new ErrorDTO(HttpStatus.BAD_REQUEST, "Validation error");

        dto.setDetailedMessages(Collections.singletonList(exception.getMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> AccountNotFoundExceptionHandler(NotFoundException exception) {
        ErrorDTO dto = new ErrorDTO(HttpStatus.NOT_FOUND, "Resourse not found error");

        dto.setDetailedMessages(Collections.singletonList(exception.getMessage()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }
}