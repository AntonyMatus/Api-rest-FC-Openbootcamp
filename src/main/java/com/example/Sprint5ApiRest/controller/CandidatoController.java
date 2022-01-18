package com.example.Sprint5ApiRest.controller;

import com.example.Sprint5ApiRest.entities.Candidato;
import com.example.Sprint5ApiRest.services.CandidatoService;
import com.example.Sprint5ApiRest.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class CandidatoController {

    @Autowired
    private final CandidatoService candidatoService;
    @Autowired
    private  final UsuarioService usuarioService;

    public CandidatoController(CandidatoService candidatoService, UsuarioService usuarioService) {
        this.candidatoService = candidatoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("api/candidato/findbyid/{id}")
    public Optional<Candidato> findCandidato(@PathVariable("id") Long id){
        return candidatoService.findById(id);
    }

    @GetMapping("api/candidato/findall")
    public List<Candidato> findAllCandidatos(){
        return candidatoService.findAll();
    }

    @PostMapping("api/candidato/save/{email}")
    public Candidato gardarCandidato(@PathVariable("email") String email, @RequestBody Candidato candidato){
        candidato.setUsuario(usuarioService.findByEmail(email));
        return candidatoService.saveCandidato(candidato);
    }

    @PutMapping("api/candidato/update")
    public Candidato actualizarCandidato(@RequestBody Candidato candidato){
        candidatoService.update(candidato);
        return candidato;
    }

    @DeleteMapping("api/candidato/deletebyid/{id}")
    public void eliminarCandidato(@PathVariable("id") Long id){
        candidatoService.deleteById(id);
    }

    @DeleteMapping("api/candidato/deleteall")
    public void eliminarTodosLosCandidatos(){
        candidatoService.deleteAll();
    }
}

