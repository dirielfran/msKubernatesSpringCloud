package com.eareiza.ms.usuarios.controllers;

import com.eareiza.ms.usuarios.entitys.Usuario;
import com.eareiza.ms.usuarios.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Usuario crear(@RequestBody Usuario usuario){
        return usuarioService.guardar(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Usuario usuario, @PathVariable Long id){
        Optional<Usuario> opt = usuarioService.porId(id);
        if(opt.isPresent()){
            Usuario usuarioDB = opt.get();
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


}
