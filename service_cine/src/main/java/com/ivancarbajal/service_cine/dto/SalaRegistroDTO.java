package com.ivancarbajal.service_cine.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SalaRegistroDTO {

    @NotBlank(message = "El nombre de la sala no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre de la sala debe tener entre 3 y 100 caracteres")
    private String nombreSala;

    @NotNull(message = "La capacidad total de la sala no puede estar vacía")
    @Min(value = 1, message = "La capacidad total de la sala debe ser mayor a 0")
    private Integer capacidadTotal;
}
