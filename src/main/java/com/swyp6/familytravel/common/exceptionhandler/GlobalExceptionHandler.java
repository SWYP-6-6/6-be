package com.swyp6.familytravel.common.exceptionhandler;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Order(value = 1)
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFoundException(Exception exception) {
        log.error("EntityNotFoundException: {} {}", exception.getMessage(), exception.getStackTrace());
        return ResponseEntity
                .status(400)
                .body(
                        exception.getMessage()
                );
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> globalException(Exception exception) {
        log.error("EntityNotFoundException: {} {}", exception.getMessage(), exception.getStackTrace());

        return ResponseEntity
                .status(500)
                .body(
                        exception.getMessage()
                );
    }
}

