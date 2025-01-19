package com.ryu.note_backend.configuration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest()
                .body(exception.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler({ BadCredentialsException.class, LockedException.class, UsernameNotFoundException.class })
    public ResponseEntity<String> serviceException(Exception exception) {
        return ResponseEntity.badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeException(RuntimeException exception) {
        return ResponseEntity.internalServerError()
                .body("Something went wrong");
    }
}