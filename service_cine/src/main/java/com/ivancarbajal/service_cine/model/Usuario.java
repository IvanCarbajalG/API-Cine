package com.ivancarbajal.service_cine.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Para que spring sepa que es una entidad de la base de datos, se le pone la anotación @Entity
@Entity
@Table(name = "usuario")
// Lombok es una librería que nos ayuda a reducir el código boilerplate, como
// getters, setters, constructores, etc. Con la anotación @Data, Lombok genera
// automáticamente getters, setters, toString, equals y hashCode para todos los
// campos de la clase.
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombreUsuario;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "rol", nullable = false, length = 50)
    private String rol;
}
