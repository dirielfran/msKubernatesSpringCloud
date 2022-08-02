package com.eareiza.ms.usuarios.repositories;

import com.eareiza.ms.usuarios.entitys.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
