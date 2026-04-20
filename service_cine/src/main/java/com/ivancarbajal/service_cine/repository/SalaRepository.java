package com.ivancarbajal.service_cine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivancarbajal.service_cine.model.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    Optional<Sala> findByNombreSala(String nombreSala);

    Boolean existsByNombreSala(String nombreSala);

    
}
 