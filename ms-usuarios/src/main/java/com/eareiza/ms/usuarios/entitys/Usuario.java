package com.eareiza.ms.usuarios.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo de nombre no puede ser vacio")
    private String nombre;

    @Email
    @NotEmpty(message = "El campo de nombre no puede ser vacio")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "El campo de password no puede ser vacio")
    private String password;

}
