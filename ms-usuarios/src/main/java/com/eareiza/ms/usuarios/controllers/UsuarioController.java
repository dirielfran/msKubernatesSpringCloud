package com.eareiza.ms.usuarios.controllers;

import com.eareiza.ms.usuarios.entitys.Usuario;
import com.eareiza.ms.usuarios.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetalle(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.porId(id);
        if(usuario.isPresent()) return ResponseEntity.ok(usuario.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result){
        if(usuarioService.findByEmail(usuario.getEmail()).isPresent())
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "Ya existe usuario con este email"));
        if(result.hasErrors()){
            return validaCampos(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuario));
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            return validaCampos(result);
        }
        Optional<Usuario> opt = usuarioService.porId(id);
        if(opt.isPresent()){
            Usuario usuarioDB = opt.get();
            if(!usuario.getEmail().equalsIgnoreCase(usuarioDB.getEmail()) && usuarioService.findByEmail(usuario.getEmail()).isPresent())
                return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "Ya existe usuario con este email"));
            usuarioDB.setNombre(usuario.getNombre());
            usuarioDB.setEmail(usuario.getEmail());
            usuarioDB.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuarioDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Usuario> opt = usuarioService.porId(id);
        if(opt.isPresent()) {
            usuarioService.eliminar(id);
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validaCampos(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo "+ err.getField() + ": " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }


}
