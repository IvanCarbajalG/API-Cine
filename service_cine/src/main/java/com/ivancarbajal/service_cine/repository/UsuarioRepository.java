package com.ivancarbajal.service_cine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ivancarbajal.service_cine.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aquí puedes agregar métodos personalizados de consulta si es necesario
    Optional<Usuario> findByEmail(String email);

    Boolean existsByEmail(String email);
}
