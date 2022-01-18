package com.example.Sprint5ApiRest.services;

import com.example.Sprint5ApiRest.entities.Candidato;
import com.example.Sprint5ApiRest.entities.Usuario;
import com.example.Sprint5ApiRest.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Service
public class UsuarioImplService implements UsuarioService {


    private final UsuarioRepository usuarioRepository;
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);




    public UsuarioImplService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        String hash = argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findByEmail(String email) {
        for (Usuario usuario : usuarioRepository.findAll()){
            if(Objects.equals(usuario.getEmail(), email)){
                return usuario;
            }
        }
        return null;
    }

    @Override
    public Usuario update(Usuario usuario) {

       String hash =  argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();
        return usuarioRepository.findById(id);
    }

    @Override
    public boolean deleteAll() {
        usuarioRepository.deleteAll();
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null || !usuarioRepository.existsById(id)){
            return false;
        }
        usuarioRepository.deleteById(id);
        return true;

    }

    @Override
    public boolean login(String email, String password) {
        for (Usuario usuario : usuarioRepository.findAll()){
            if (Objects.equals(email, usuario.getEmail()) && argon2.verify(usuario.getPassword(), password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario addCandidato(String email, Candidato candidato) {
        for (Usuario usuario : usuarioRepository.findAll()) {
            if (Objects.equals(usuario.getEmail(), email)){
                usuario.getCandidatos().add(candidato);
                usuarioRepository.save(usuario);
                return usuario;
            }
        }
        return null;
    }
}
