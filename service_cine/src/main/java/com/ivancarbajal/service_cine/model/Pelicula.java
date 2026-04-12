package com.ivancarbajal.service_cine.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pelicula")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula")
    private Long idPelicula;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "genero", nullable = false, length = 50)
    private String genero;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Column(name = "clasificacion", nullable = false, length = 10)
    private String clasificacion;

    @Column(name = "sinopsis", nullable = false, columnDefinition = "TEXT")
    private String sinopsis;

}