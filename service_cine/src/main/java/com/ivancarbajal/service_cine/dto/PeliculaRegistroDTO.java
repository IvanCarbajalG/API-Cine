package com.ivancarbajal.service_cine.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PeliculaRegistroDTO {

    @NotBlank(message = "El título de la película no puede estar vacío")
    @Size(min = 3, max = 150, message = "El título de la película debe tener entre 3 y 100 caracteres")
    private String titulo;

    @NotBlank(message = "El género de la película no puede estar vacío")
    private String genero;

    @NotNull(message = "La duración de la película no puede estar vacía")
    @Min(value = 1, message = "La duración de la película debe ser mayor a 0 minutos")
    private Integer duracion;

    @NotBlank(message = "La clasificación de la película no puede estar vacía")
    private String clasificacion;

    @Size(max = 255, message = "La sinopsis de la película no puede exceder los 255 caracteres")
    private String sinopsis;
}