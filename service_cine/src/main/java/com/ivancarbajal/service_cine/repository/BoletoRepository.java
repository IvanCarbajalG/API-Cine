package com.ivancarbajal.service_cine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivancarbajal.service_cine.model.Boleto;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long> {

    Optional<Boleto> findById(Long id);
}