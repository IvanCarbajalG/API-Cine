package com.ivancarbajal.service_cine.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ivancarbajal.service_cine.dto.PeliculaRegistroDTO;
import com.ivancarbajal.service_cine.model.Pelicula;
import com.ivancarbajal.service_cine.repository.PeliculaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public void registrarPelicula(PeliculaRegistroDTO dto) {
        if (peliculaRepository.existsByTitulo(dto.getTitulo())) {
            throw new IllegalArgumentException("Ya existe una película con ese título");
        }
        // Lógica para registrar la película
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(dto.getTitulo());
        pelicula.setGenero(dto.getGenero());
        pelicula.setDuracion(dto.getDuracion());
        pelicula.setClasificacion(dto.getClasificacion());
        pelicula.setSinopsis(dto.getSinopsis());
        peliculaRepository.save(pelicula);
    }

    public List<Pelicula> listarPeliculas() {
        return peliculaRepository.findAll();
    }
}