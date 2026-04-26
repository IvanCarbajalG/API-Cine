package com.ivancarbajal.service_cine.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ivancarbajal.service_cine.dto.ReservaRequestDTO;
import com.ivancarbajal.service_cine.model.Asiento;
import com.ivancarbajal.service_cine.model.Boleto;
import com.ivancarbajal.service_cine.model.Funcion;
import com.ivancarbajal.service_cine.model.Reserva;
import com.ivancarbajal.service_cine.model.Usuario;
import com.ivancarbajal.service_cine.repository.AsientoRepository;
import com.ivancarbajal.service_cine.repository.FuncionRepository;
import com.ivancarbajal.service_cine.repository.ReservaRepositorio;
import com.ivancarbajal.service_cine.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepositorio reservaRepositorio;
    private final UsuarioRepository usuarioRepository;
    private final FuncionRepository funcionRepository;
    private final AsientoRepository asientoRepository;

    @Transactional
    public Reserva crearReservaCompleta(ReservaRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Funcion funcion = funcionRepository.findById(dto.funcionId())
                .orElseThrow(() -> new RuntimeException("Funcion no encontrada"));

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setFechaReserva(LocalDateTime.now());
        reserva.setEstado("PENDIENTE");
        reserva.setBoletos(new ArrayList<>());

        for (long asientoId : dto.asientosIds()) {
            Asiento asiento = asientoRepository.findById(asientoId)
                    .orElseThrow(() -> new RuntimeException("El asiento con ID: " + asientoId + "no existe"));
            //
            if (!asiento.getSala().getIdSala().equals(funcion.getSala().getIdSala())) {
                throw new RuntimeException(
                        "El asiento" + asiento.getNumero() + " no pertenece a la sala de esta funcion.");
            }

            Boleto boleto = new Boleto();
            boleto.setReserva(reserva);
            boleto.setFuncion(funcion);
            boleto.setAsiento(asiento);
            boleto.setPrecioAplicado(funcion.getPrecio());
            reserva.getBoletos().add(boleto);
        }
        return reservaRepositorio.save(reserva);
    }
}
