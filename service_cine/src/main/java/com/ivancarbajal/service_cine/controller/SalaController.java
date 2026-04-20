package com.ivancarbajal.service_cine.controller;

import com.ivancarbajal.service_cine.service.SalaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivancarbajal.service_cine.dto.SalaRegistroDTO;
import com.ivancarbajal.service_cine.model.Sala;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/sala")
@RequiredArgsConstructor
public class SalaController {

    private final SalaService salaService;

    @PostMapping("/registro")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registrarSala(@Valid @RequestBody SalaRegistroDTO dto) {
        try {
            salaService.registrarSala(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Sala registrada con exito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/allSalas")
    public ResponseEntity<List<Sala>> listar() {
        try {
            List<Sala> salas = salaService.listarSalas();
            return ResponseEntity.ok(salas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
