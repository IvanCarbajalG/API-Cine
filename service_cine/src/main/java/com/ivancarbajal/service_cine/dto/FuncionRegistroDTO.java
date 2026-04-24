package com.ivancarbajal.service_cine.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FuncionRegistroDTO {

    @NotNull(message = "La hora y fecha son obligatorias")
    @Future(message = "La hora y fecha deben ser futuras")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // Formato para JSON
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // Formato para Spring
    private LocalDateTime horaFecha;

    @NotNull(message = "El precio es obligatorio")
    private Double precio;

    @NotNull(message = "El ID de la película es obligatorio")
    private Long peliculaId;

    @NotNull(message = "El ID de la sala es obligatorio")
    private Long salaId;
}
