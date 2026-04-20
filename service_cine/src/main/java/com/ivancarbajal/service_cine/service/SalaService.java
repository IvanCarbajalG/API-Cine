package com.ivancarbajal.service_cine.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ivancarbajal.service_cine.dto.SalaRegistroDTO;
import com.ivancarbajal.service_cine.model.Sala;
import com.ivancarbajal.service_cine.repository.SalaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository salaRepository;

    public void registrarSala(SalaRegistroDTO dto) {
        // Validar que no exista una sala con el mismo nombre
        if (salaRepository.existsByNombreSala(dto.getNombreSala())) {
            throw new IllegalArgumentException("Ya existe una sala con ese nombre");
        }
        // Lógica para registrar la sala
        Sala sala = new Sala();
        sala.setNombreSala(dto.getNombreSala());
        sala.setCapacidadTotal(dto.getCapacidadTotal());
        salaRepository.save(sala);
    }

    public List<Sala> listarSalas() {
        return salaRepository.findAll();
    }

    public Sala obtenerSalaPorNombre(String nombreSala) {
        return salaRepository.findByNombreSala(nombreSala)
                .orElseThrow(() -> new IllegalArgumentException("No existe una sala con el nombre: " + nombreSala));
    }

    
}
