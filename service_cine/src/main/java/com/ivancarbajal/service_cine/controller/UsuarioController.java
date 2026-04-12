package com.ivancarbajal.service_cine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivancarbajal.service_cine.dto.UsuarioRegistroDTO;
import com.ivancarbajal.service_cine.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController //Le decimos a Spring que esto recibe peticiones HTTP y devuelve respuestas HTTP
@RequestMapping("/api/usuarios") //Define la ruta base para todas las peticiones relacionadas con usuarios
@RequiredArgsConstructor //Inyecta UsuarioService por el controlador
public class UsuarioController {

    private final UsuarioService usuarioService; 

    @PostMapping("/registro") 
    public ResponseEntity<String> registrar(@Valid @RequestBody UsuarioRegistroDTO dto) {
        try {
            usuarioService.registrarUsuario(dto);
            return ResponseEntity.ok("Usuario registrado con exito");
        } catch (IllegalArgumentException e) {
            //Errores de validación, como email ya registrado o datos faltantes
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            //Culaquier error en la base de datos o en el servidor
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}
