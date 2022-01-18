package com.example.Sprint5ApiRest.controller;


import com.example.Sprint5ApiRest.entities.Usuario;
import com.example.Sprint5ApiRest.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("api/usuario/findbyid/{id}")
    public Optional<Usuario> findUsuario(@PathVariable("id") Long id){
        return usuarioService.findById(id);
    }

    @GetMapping("api/usuario/findbyemail/{email}")
    public Usuario findUsuario(@PathVariable("email") String email){
        return usuarioService.findByEmail(email);
    }

    @GetMapping("api/usuario/findall")
    public List<Usuario> findUsuario() {
        return usuarioService.findAll();
    }

    @PostMapping("api/usuario")
    public Usuario GuardarUsuario(@RequestBody Usuario usuario){
        usuarioService.saveUsuario(usuario);
        return usuario;
    }

    @PutMapping("api/usuario/update")
    public Usuario actualizarUsuario(@RequestBody Usuario usuario){


        usuarioService.update(usuario);
        return usuario;
    }

    @DeleteMapping("/api/usuario/deletebyid/{id}")
    public void deleteUsuario(@PathVariable("id") Long id){
        usuarioService.deleteById(id);
    }

    @DeleteMapping("api/usuario/deleteall")
    public void deleteUsuario(){
        usuarioService.deleteAll();
    }


    @PostMapping("api/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario){
        if (usuarioService.login(usuario.getEmail(), usuario.getPassword())){
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.badRequest().build();
    }
}

