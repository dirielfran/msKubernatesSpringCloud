package com.eareiza.ms.usuarios.repositories;

import com.eareiza.ms.usuarios.entitys.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
