package com.ivancarbajal.service_cine.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ivancarbajal.service_cine.dto.PeliculaRegistroDTO;
import com.ivancarbajal.service_cine.model.Pelicula;
import com.ivancarbajal.service_cine.service.PeliculaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/peliculas")
@RequiredArgsConstructor
public class PeliculaController {

    private final PeliculaService peliculaService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> crearPelicual(@Valid @RequestBody PeliculaRegistroDTO dto) {
        peliculaService.registrarPelicula(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pelicula registrada correctamente");
    }

    @GetMapping
    public ResponseEntity<List<Pelicula>> listar() {
        return ResponseEntity.ok(peliculaService.listarPeliculas());
    }
    

}
