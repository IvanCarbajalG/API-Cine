package com.ivancarbajal.service_cine.service;

import org.springframework.stereotype.Service;

import com.ivancarbajal.service_cine.dto.UsuarioRegistroDTO;
import com.ivancarbajal.service_cine.model.Usuario;
import com.ivancarbajal.service_cine.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor // Lombok para generar el constructor con los campos finales
public class UsuarioService {

    // Al ser un campo final, Lombok generará un constructor que lo inicialice, y
    // Spring lo inyectará automáticamente
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // 1. logica de negocio verifica si el email ya existe
    public void registrarUsuario(UsuarioRegistroDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado");
        }
        // mapeamos el DTO a la entidad Usuario
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());

        // 2. Segiridad encriptar la contraseña antes de guardarla
        String contraseñaEncriptada = passwordEncoder.encode(dto.getPassword());
        usuario.setPassword(contraseñaEncriptada);

        usuarioRepository.save(usuario);
    }
}