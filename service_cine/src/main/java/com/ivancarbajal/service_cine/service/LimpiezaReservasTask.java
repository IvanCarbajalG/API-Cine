package com.ivancarbajal.service_cine.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ivancarbajal.service_cine.model.Reserva;
import com.ivancarbajal.service_cine.repository.ReservaRepositorio;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j // Para mostrar en consola lo que pasa
public class LimpiezaReservasTask {
    private final ReservaRepositorio reservaRepositorio;

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void limpiarReservasVencidas() {
        // Definimos la tolerancia para pagar: 5 minutos
        LocalDateTime tiempoLimite = LocalDateTime.now().minusMinutes(5);

        List<Reserva> reservasVencidas = reservaRepositorio.findByEstadoAndFechaReservaBefore("PENDIENTE",
                tiempoLimite);

        if (!reservasVencidas.isEmpty()) {
            log.info("Se encontraron {} RESERVAS VENCIDAS. Porcediendo a liberar asientos...", reservasVencidas.size());

            // Se borra la reserva y con ella los boeletos por el CascadeType.ALL
            for (Reserva reserva : reservasVencidas) {
                reservaRepositorio.delete(reserva);
            }
        }
    }
}
