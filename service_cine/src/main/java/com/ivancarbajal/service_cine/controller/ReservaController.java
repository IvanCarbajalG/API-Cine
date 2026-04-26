package com.ivancarbajal.service_cine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivancarbajal.service_cine.dto.ReservaRequestDTO;
import com.ivancarbajal.service_cine.service.ReservaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearReserva(@RequestBody ReservaRequestDTO dto) {
        try {
            reservaService.crearReservaCompleta(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Reserva creada con exito");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
