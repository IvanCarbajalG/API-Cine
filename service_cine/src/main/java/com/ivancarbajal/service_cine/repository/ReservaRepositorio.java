package com.ivancarbajal.service_cine.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivancarbajal.service_cine.model.Reserva;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {
    Optional<Reserva> findById(Long id);

    List<Reserva> findByEstadoAndFechaReservaBefore(String estado, LocalDateTime tiempoLimite);
    
}
