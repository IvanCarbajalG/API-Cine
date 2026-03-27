package com.ivancarbajal.service_cine.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice // Escucha todas las excepciones de los controladores
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) // Maneja errores de validación de Spring
    public ResponseEntity<java.util.Map<String, String>> manejarValidaciones(MethodArgumentNotValidException ex) {
        java.util.Map<String, String> errores = new java.util.HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(IllegalArgumentException.class) // Maneja errores personalizados como email ya registrado
    public ResponseEntity<String> manejarIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
