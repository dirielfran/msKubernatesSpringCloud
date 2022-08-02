package com.eareiza.mscursos.services;

import com.eareiza.mscursos.entities.Curso;
import com.eareiza.mscursos.interfaces.CursoService;
import com.eareiza.mscursos.reposories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>) cursoRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> porId(Long id) {
        return cursoRepo.findById(id);
    }

    @Override
    @Transactional
    public Curso guardar(Curso usuario) {
        return cursoRepo.save(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        cursoRepo.deleteById(id);
    }
}
