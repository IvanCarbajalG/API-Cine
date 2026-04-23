package com.ivancarbajal.service_cine.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ivancarbajal.service_cine.dto.AsientoRegistroDTO;
import com.ivancarbajal.service_cine.model.Asiento;
import com.ivancarbajal.service_cine.model.Sala;
import com.ivancarbajal.service_cine.repository.AsientoRepository;
import com.ivancarbajal.service_cine.repository.SalaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsientoService {

    private final AsientoRepository asientoRepository;
    private final SalaRepository salaRepository;

    public void registrarAsiento(AsientoRegistroDTO dto) {
        // Validar que la sala exista
        Sala sala = salaRepository.findById(dto.getSalaId())
                .orElseThrow(() -> new IllegalArgumentException("No existe una sala con el ID: " + dto.getSalaId()));

        if (asientoRepository.existsByFilaAndNumeroAndSala(dto.getFila(), dto.getNumero(), sala)) {
            throw new IllegalArgumentException("Ya existe un asiento con esa fila, número y sala");
        }
        // Lógica para registrar el asiento
        Asiento asiento = new Asiento();
        asiento.setFila(dto.getFila());
        asiento.setNumero(dto.getNumero());
        asiento.setSala(sala);
        asientoRepository.save(asiento);
    }

    public Asiento obtenerAsientoPorFilaYNumero(String fila, Integer numero) {
        return asientoRepository.findByFilaAndNumero(fila, numero)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe un asiento con la fila: " + fila + " y número: " + numero));
    }

    public List<Asiento> listarAsientos() {
        return asientoRepository.findAll();
    }
}
