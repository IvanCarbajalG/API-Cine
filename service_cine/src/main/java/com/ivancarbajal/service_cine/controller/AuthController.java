package com.ivancarbajal.service_cine.controller;

import com.ivancarbajal.service_cine.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivancarbajal.service_cine.dto.LoginDTO;
import com.ivancarbajal.service_cine.service.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return usuarioRepository.findByEmail(loginDTO.getEmail())
                .map(usuario -> {
                    if (passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword())) {
                        String token = jwtService.generarToken(usuario.getEmail());
                        return ResponseEntity.ok(token);
                    }
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
                })
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas"));
    }
}
