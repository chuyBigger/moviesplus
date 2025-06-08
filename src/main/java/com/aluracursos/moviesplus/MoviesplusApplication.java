package com.aluracursos.moviesplus;

import com.aluracursos.moviesplus.modelos.DatosEpisodio;
import com.aluracursos.moviesplus.modelos.DatosSerie;
import com.aluracursos.moviesplus.modelos.DatosTemporadas;
import com.aluracursos.moviesplus.service.ConsumoApi;
import com.aluracursos.moviesplus.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MoviesplusApplication implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {

		var consumoApi = new ConsumoApi();
		var json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=vikings&season=1&apikey=58237260");
		System.out.println(json);
		ConvierteDatos convierteDatos = new ConvierteDatos();
		var datos = convierteDatos.obtenerDatos(json, DatosSerie.class);
		System.out.println(datos);
		json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=vikings&season=1&episode=1&apikey=58237260");
		DatosEpisodio datosEpisodio = convierteDatos.obtenerDatos(json, DatosEpisodio.class);
		System.out.println(datosEpisodio);

		List<DatosTemporadas> temporadas = new ArrayList<>();
		for (int i = 1; i <= datos.totalDeTemporadas() ; i++) {
			json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&season="+i+"&apikey=58237260");
			var datosTemporadas = convierteDatos.obtenerDatos(json, DatosTemporadas.class);
			temporadas.add(datosTemporadas);
		}
		temporadas.forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(MoviesplusApplication.class, args);
	}


}
