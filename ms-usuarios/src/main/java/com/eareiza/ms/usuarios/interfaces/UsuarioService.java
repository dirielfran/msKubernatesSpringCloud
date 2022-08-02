package com.eareiza.ms.usuarios.interfaces;

import com.eareiza.ms.usuarios.entitys.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listar();
    Optional<Usuario> porId(Long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);

    Optional<Usuario> findByEmail(String email);
}
