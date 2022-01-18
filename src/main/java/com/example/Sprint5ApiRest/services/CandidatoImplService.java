package com.example.Sprint5ApiRest.services;

import com.example.Sprint5ApiRest.entities.Candidato;
import com.example.Sprint5ApiRest.repositories.CandidatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatoImplService implements CandidatoService {

    private  final CandidatoRepository candidatoRepository;

    public CandidatoImplService(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    @Override
    public Candidato saveCandidato(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    @Override
    public List<Candidato> findAll() {
        return candidatoRepository.findAll();
    }

    @Override
    public Optional<Candidato> findById(Long id) {
        if (id == null || id <= 0) {
            return Optional.empty();

        }
        return candidatoRepository.findById(id);
    }

    @Override
    public Candidato update(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    @Override
    public boolean deleteById(Long id) {
       if (id == null || !candidatoRepository.existsById(id)) {
           return false;
       }
       candidatoRepository.deleteById(id);
       return true;
    }

    @Override
    public boolean deleteAll() {
        candidatoRepository.deleteAll();
        return true;
    }
}
