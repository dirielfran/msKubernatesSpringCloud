package com.eareiza.mscursos.controllers;

import com.eareiza.mscursos.entities.Curso;
import com.eareiza.mscursos.interfaces.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> listar(){
        return cursoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetalle(@PathVariable Long id){
        Optional<Curso> Curso = cursoService.porId(id);
        if(Curso.isPresent()) return ResponseEntity.ok(Curso.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result){
        if(result.hasErrors()) return validaCampos(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(curso));
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso Curso, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()) return validaCampos(result);
        Optional<Curso> opt = cursoService.porId(id);
        if(opt.isPresent()){
            Curso cursoDB = opt.get();
            cursoDB.setNombre(Curso.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(cursoDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Curso> opt = cursoService.porId(id);
        if(opt.isPresent()) {
            cursoService.eliminar(id);
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validaCampos(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() +": "+err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
