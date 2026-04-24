package com.ivancarbajal.service_cine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivancarbajal.service_cine.dto.FuncionRegistroDTO;
import com.ivancarbajal.service_cine.service.FuncionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/funcion")
@RequiredArgsConstructor
public class FuncionController {

    private final FuncionService funcionService;

    @PostMapping("/registro")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registarSala(@Valid @RequestBody FuncionRegistroDTO dto) {
        try {
            funcionService.registrarFuncion(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Función registrada con exito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}
