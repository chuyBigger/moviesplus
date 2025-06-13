package com.aluracursos.moviesplus.principal;

import com.aluracursos.moviesplus.modelos.*;
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
    private static final String API_KEY = "&apikey=58237260";
    private List<DatosSerie> datosSeries = new ArrayList<>();

    public void muestraElMenu(){
        var opcion = -1;
        while (opcion != 0){
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

            switch (opcion){
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
                    System.out.println(" Gracias por usar nuestra app hasta luego...");
                    break;
                default:
                    System.out.println("Opcion No valida...");
            }

        }
    }


    private DatosSerie getDatosSerie(){
        System.out.println("Escribe el Nombre de la serie que buscas: ");
        var nombreSerie = scanner.nextLine();
        var nombreSerieCodifiado = URLEncoder.encode(nombreSerie, StandardCharsets.UTF_8);
        var json = consumoApi.obtenerDatos(API_BASE+nombreSerieCodifiado+API_KEY);
        System.out.println(json);
        DatosSerie datos = convierteDatos.obtenerDatos(json, DatosSerie.class);
        return datos;
    }

    private void buscarEpisodioPorSerie(){
        DatosSerie datosSerie = getDatosSerie();
        List<DatosTemporadas> temporadas = new ArrayList<>();

        for (int i = 1; i <= datosSerie.totalDeTemporadas(); i++) {
            var tituloCodificado = URLEncoder.encode(datosSerie.titulo(), StandardCharsets.UTF_8);
            var json = consumoApi.obtenerDatos(API_BASE+tituloCodificado+API_KEY);
            DatosTemporadas datosTemporadas = convierteDatos.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        temporadas.forEach(System.out::println);
    }

    private void buscarSerieWeb(){
        DatosSerie datos = getDatosSerie();
        datosSeries.add(datos);
        System.out.println(datos);

    }

    private void mostrarSeriesBuscadas() {
        List<Serie> series = new ArrayList<>();
        series = datosSeries.stream()
                .map(d -> new Serie(d))
                .collect(Collectors.toList());

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);


    }


}
