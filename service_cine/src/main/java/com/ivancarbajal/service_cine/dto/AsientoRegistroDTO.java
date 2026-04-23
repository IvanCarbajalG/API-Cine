package com.ivancarbajal.service_cine.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AsientoRegistroDTO {

    @NotBlank(message = "La fila del asiento no puede estar vacía")
    @Size(min = 1, max = 1, message = "La fila del asiento debe ser un solo carácter")
    private String fila;

    @NotNull(message = "El número del asiento no puede estar vacío")
    private Integer numero;

    @NotNull(message = "El ID de la sala no puede estar vacío")
    private Long salaId;
}
