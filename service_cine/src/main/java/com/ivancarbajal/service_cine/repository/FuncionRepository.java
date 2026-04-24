package com.ivancarbajal.service_cine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivancarbajal.service_cine.model.Funcion;
import com.ivancarbajal.service_cine.model.Sala;

@Repository
public interface FuncionRepository extends JpaRepository<Funcion, Long> {

        Optional<Funcion> findById(Long idFuncion);

        List<Funcion> findBySala(Sala sala);
}
