package com.aluracursos.moviesplus.principal;

import com.aluracursos.moviesplus.modelos.*;
import com.aluracursos.moviesplus.repositorio.SerieRepository;
import com.aluracursos.moviesplus.service.ConsumoApi;
import com.aluracursos.moviesplus.service.ConvierteDatos;


import java.beans.Encoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    Scanner scanner = new Scanner(System.in);
    ConsumoApi consumoApi = new ConsumoApi();
    ConvierteDatos convierteDatos = new ConvierteDatos();
    private static final String API_BASE = "https://www.omdbapi.com/?t=";
    private String apikey;
    private List<DatosSerie> datosSeries = new ArrayList<>();
    private SerieRepository repositorio;
    private List<Serie> series = new ArrayList<>();

    public Principal(SerieRepository repository, String apikey) {
        this.repositorio = repository;
        this.apikey = apikey;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                     ==============================================
                     ªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªªª
                                1.- Buscar Series
                                2.- Buscar Episodios
                                3.- Mostrar Series Buscadas
                    
                                0.- Salir;
                    
                    ===============================================                                
                    
                    """;
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    mostrarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Gracias por usar nuestra app hasta luego...");
                    break;
                default:
                    System.out.println("Opcion No valida...");
            }

        }
    }

    private DatosSerie getDatosSerie() {
        System.out.println("Escribe el Nombre de la serie que buscas: ");
        var nombreSerie = scanner.nextLine();
        var nombreSerieCodifiado = URLEncoder.encode(nombreSerie, StandardCharsets.UTF_8);
        var json = consumoApi.obtenerDatos(API_BASE + nombreSerieCodifiado + apikey);
        System.out.println(json);
        DatosSerie datos = convierteDatos.obtenerDatos(json, DatosSerie.class);
        return datos;
    }

    private void buscarEpisodioPorSerie() {
        mostrarSeriesBuscadas();
        System.out.println("Esribe el nombre de la serie de la cual quieres ver los episodios?");
        var nombreSerie = scanner.nextLine();

        Optional<Serie> serie = series.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nombreSerie.toLowerCase()))
                .findFirst();

        if (serie.isPresent()) {
            var serieEncontrada = serie.get();
            List<DatosTemporadas> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalDeTemporadas(); i++) {
                var tituloCodificado = URLEncoder.encode(serieEncontrada.getTitulo(), StandardCharsets.UTF_8);
                var json = consumoApi.obtenerDatos(API_BASE + tituloCodificado + "&season=" + i + apikey);
                DatosTemporadas datosTemporadas = convierteDatos.obtenerDatos(json, DatosTemporadas.class);
                temporadas.add(datosTemporadas);
            }
            temporadas.forEach(System.out::println);
            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
        }
    }

    private void buscarSerieWeb() {
        DatosSerie datos = getDatosSerie();
        Serie serie = new Serie(datos);
        repositorio.save(serie);
        // datosSeries.add(datos);
        System.out.println(datos);

    }

    private void mostrarSeriesBuscadas() {
        series = repositorio.findAll();

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }
}
