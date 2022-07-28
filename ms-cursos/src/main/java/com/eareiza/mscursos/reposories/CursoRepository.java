package com.eareiza.mscursos.reposories;

import com.eareiza.mscursos.entities.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {
}
