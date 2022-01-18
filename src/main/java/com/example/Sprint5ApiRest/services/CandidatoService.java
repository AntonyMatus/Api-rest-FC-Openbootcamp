package com.example.Sprint5ApiRest.services;

import com.example.Sprint5ApiRest.entities.Candidato;

import java.util.List;
import java.util.Optional;

public interface CandidatoService {

    Candidato saveCandidato(Candidato candidato);
    List<Candidato> findAll();
    Optional<Candidato> findById(Long id);
    Candidato update(Candidato candidato);
    boolean deleteById(Long id);
    boolean deleteAll();

}
