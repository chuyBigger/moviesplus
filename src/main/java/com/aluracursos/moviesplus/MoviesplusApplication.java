package com.aluracursos.moviesplus;

import com.aluracursos.moviesplus.principal.EjemploStrems;
import com.aluracursos.moviesplus.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MoviesplusApplication implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();
		principal.muestraElMenu();

//		EjemploStrems ejemploStrems = new EjemploStrems();
//		ejemploStrems.muestraEjemplo();

	}

	public static void main(String[] args) {
		SpringApplication.run(MoviesplusApplication.class, args);
	}


}
