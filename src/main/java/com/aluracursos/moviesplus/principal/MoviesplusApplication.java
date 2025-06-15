package com.aluracursos.moviesplus.principal;

import com.aluracursos.moviesplus.repositorio.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Value;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.aluracursos.moviesplus.repositorio")
@EntityScan(basePackages = "com.aluracursos.moviesplus.modelos")
public class MoviesplusApplication implements CommandLineRunner {

    @Autowired
    private SerieRepository repository;

    @Value("${API_KEY_OMDB}")
    private String apikey;

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("✅ Driver PostgreSQL cargado correctamente.");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ ERROR: Driver PostgreSQL NO encontrado.");
            e.printStackTrace();
        }

        SpringApplication.run(MoviesplusApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(repository, apikey);
        principal.muestraElMenu();
    }


}
