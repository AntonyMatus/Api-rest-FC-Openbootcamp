package com.example.Sprint5ApiRest;

import com.example.Sprint5ApiRest.entities.Candidato;
import com.example.Sprint5ApiRest.entities.Etiqueta;
import com.example.Sprint5ApiRest.entities.Usuario;
import com.example.Sprint5ApiRest.repositories.CandidatoRepository;
import com.example.Sprint5ApiRest.repositories.EtiquetaRepository;
import com.example.Sprint5ApiRest.repositories.UsuarioRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Sprint5ApiRestApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Sprint5ApiRestApplication.class, args);

		CandidatoRepository candidatoRepository = context.getBean(CandidatoRepository.class);
		UsuarioRepository usuarioRepository = context.getBean(UsuarioRepository.class);
		EtiquetaRepository etiquetaRepository = context.getBean(EtiquetaRepository.class);

		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

		Set<Etiqueta> etiquetas1 = new HashSet<>();

		etiquetas1.add(new Etiqueta(null, "JAVA"));
		etiquetas1.add(new Etiqueta(null, "PHP"));
		etiquetas1.add(new Etiqueta(null, "HTML Y CSS"));
		etiquetas1.add(new Etiqueta(null, "PYTHON"));
		etiquetas1.add(new Etiqueta(null, "FLUTTER"));
		etiquetas1.add(new Etiqueta(null, "ANGULAR"));

		System.out.println(etiquetas1);


		Usuario U4 = new Usuario(null, "Pedro","predo@gmail.com", argon2.hash(1,1024,1,"3456"));
		Usuario U5 = new Usuario(null, "Laura","laura@gmail.com", argon2.hash(1,1024,1,"8278"));

		usuarioRepository.save(U4);
		usuarioRepository.save(U5);

		candidatoRepository.save(new Candidato(1L,"Ramino Perez Martinez","México", "Cozumel", "898394839","ramirez@gmail.com",true, Candidato.Precencialidad.REMOTO,U4,etiquetas1));

	}

}
