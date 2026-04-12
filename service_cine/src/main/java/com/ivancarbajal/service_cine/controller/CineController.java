package com.ivancarbajal.service_cine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/cine")
public class CineController {
    @GetMapping("/cartelera")
    public ResponseEntity<String> verCartelera() {
        return ResponseEntity.ok("🎥 Cartelera: Avengers, Batman, Spiderman (Solo para usuarios con Token)  ");
    }

}
