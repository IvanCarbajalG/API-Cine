package com.ivancarbajal.service_cine.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ivancarbajal.service_cine.dto.FuncionRegistroDTO;
import com.ivancarbajal.service_cine.model.Funcion;
import com.ivancarbajal.service_cine.model.Pelicula;
import com.ivancarbajal.service_cine.model.Sala;
import com.ivancarbajal.service_cine.repository.FuncionRepository;
import com.ivancarbajal.service_cine.repository.PeliculaRepository;
import com.ivancarbajal.service_cine.repository.SalaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionService {

    private final FuncionRepository funcionRepository;
    private final PeliculaRepository peliculaRepository;
    private final SalaRepository salaRepository;

    public void registrarFuncion(FuncionRegistroDTO dto) {
        // Obtenemos la pelicula
        Pelicula pelicula = peliculaRepository.findById(dto.getPeliculaId())
                .orElseThrow(
                        () -> new IllegalArgumentException("No existe una película con el ID: " + dto.getPeliculaId()));
        // Obtenemos la sala
        Sala sala = salaRepository.findById(dto.getSalaId())
                .orElseThrow(() -> new IllegalArgumentException("No existe una sala con el ID: " + dto.getSalaId()));

        LocalDateTime inicioPeliculaNueva = dto.getHoraFecha();
        LocalDateTime finPeliculaNueva = inicioPeliculaNueva.plusMinutes(pelicula.getDuracion() + 15);

        List<Funcion> funcionesExistentes = funcionRepository.findBySala(sala);

        for (Funcion f : funcionesExistentes) {
            LocalDateTime inicioExistente = f.getHoraFecha();
            LocalDateTime finExistente = inicioExistente.plusMinutes(f.getPelicula().getDuracion() + 15);

            if (inicioPeliculaNueva.isBefore(finExistente) && finPeliculaNueva.isAfter(inicioExistente)) {
                throw new IllegalArgumentException("Choque de horario con la película: "
                        + f.getPelicula().getTitulo() + " (Termina a las " + finExistente + ")");
            }
        }
        // Registrar la función
        Funcion funcion = new Funcion();
        funcion.setPelicula(pelicula);
        funcion.setSala(sala);
        funcion.setHoraFecha(inicioPeliculaNueva);
        funcion.setPrecio(dto.getPrecio());

        funcionRepository.save(funcion);
    }
}
