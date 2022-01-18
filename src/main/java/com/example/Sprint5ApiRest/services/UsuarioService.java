package com.example.Sprint5ApiRest.services;

import com.example.Sprint5ApiRest.entities.Candidato;
import com.example.Sprint5ApiRest.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario);
    Usuario findByEmail(String email);
    Usuario update(Usuario usuario);
    Optional<Usuario> findById(Long id);
    boolean deleteAll();
    boolean deleteById(Long id);
    boolean login(String email, String password);
    List<Usuario> findAll();
    Usuario addCandidato(String email, Candidato candidato);
}
