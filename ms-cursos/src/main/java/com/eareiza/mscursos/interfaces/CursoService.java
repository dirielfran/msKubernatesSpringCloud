package com.eareiza.mscursos.interfaces;

import com.eareiza.mscursos.entities.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso usuario);
    void eliminar(Long id);
}
