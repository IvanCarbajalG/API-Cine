package com.ivancarbajal.service_cine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivancarbajal.service_cine.model.Asiento;
import com.ivancarbajal.service_cine.model.Sala;

@Repository
public interface AsientoRepository extends JpaRepository<Asiento, Long> {

    Optional<Asiento> findByFilaAndNumero(String fila, Integer numero);

    Boolean existsByFilaAndNumeroAndSala(String fila, Integer numero, Sala sala);
}
