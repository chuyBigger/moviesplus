package com.aluracursos.moviesplus.principal;

import com.aluracursos.moviesplus.modelos.DatosEpisodio;
import com.aluracursos.moviesplus.modelos.DatosSerie;
import com.aluracursos.moviesplus.modelos.DatosTemporadas;
import com.aluracursos.moviesplus.modelos.Episodio;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class EjemploStrems {

    public void muestraEjemplo() {
        List<String> nombres = Arrays.asList("Maria", "Dereck", "Genesis", "Marco", "Jesùs", "Maria", "Abram");

        nombres.stream()
                .sorted()
                //.limit(3)
                .filter(n -> n.startsWith("M"))
                .map(n -> n.toUpperCase())
                .forEach(System.out::println);
    }
}

//    public void muestrElMenu() {
//        System.out.println("Escribe el nombre de la serie que buscas: ");
//        // Busca los datos generales de las series
//        var busquedaSerie = scanner.nextLine();
//        var busquedaSerieCodificada = URLEncoder.encode(busquedaSerie, StandardCharsets.UTF_8);
//        var json = consumoApi.obtenerDatos(API_BASE + busquedaSerieCodificada + API_KEY);
//        var datos = convierteDatos.obtenerDatos(json, DatosSerie.class);
/// /        System.out.println(datos);
//
//        // Busca los datos de todas las temporadas
//        List<DatosTemporadas> temporadas = new ArrayList<>();
//        for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
//            json = consumoApi.obtenerDatos(API_BASE+busquedaSerieCodificada+"&season="+i+API_KEY);
//            var datosTemporadas = convierteDatos.obtenerDatos(json, DatosTemporadas.class);
//            temporadas.add(datosTemporadas);
//        }
//        //temporadas.forEach(System.out::println);
//
//        // Mostrar solo el titulo de los episodios para las temporadas
////        for (int i = 0; i < datos.totalDeTemporadas() ; i++) {
////            System.out.println("\nTEMPORADA: "+(i+1)+"\n");
////            List<DatosEpisodio> datosEpisodios = temporadas.get(i).episodios();
////            for (int j = 0; j < datosEpisodios.size(); j++) {
////                System.out.println(datosEpisodios.get(j).titulo());
////            }
////        }
////        temporadas.forEach(t -> {
////            System.out.println("\nTemporada: "+t.numero()+"\n");
////            t.episodios().forEach(e-> System.out.println(e.titulo()));
////        });
//
//        //Convertir todas las formaciones a una lista del tipo DatosEpisodio
//
//        List<DatosEpisodio> datosEpisodios = temporadas.stream()
//                .flatMap(t ->t.episodios().stream())
//                .collect(Collectors.toList());
//
////        // top 5 episodios
////        System.out.println("Top 5 spisodios!! ");
////        datosEpisodios.stream()
////                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
////                .peek(e -> System.out.println("Primer filtro N/A" + e))
////                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
////                .peek(e -> System.out.println("acomoda por orden alfabettic cpmara" + e))
////                .limit(5)
////                .peek(e -> System.out.println("Primer filtro N/A" + e))
////                .forEach(System.out::println);
//
//        // Conviertiendo los datos a una lista del tipo Episodio
//
//
//        System.out.println("Lista de datos Tipo Episodio");
//        List<Episodio> episodios = temporadas.stream()
//                .flatMap(t ->t.episodios().stream()
//                        .map(d -> new Episodio(t.numero(),d)))
//                .collect(Collectors.toList());
//        //   episodios.forEach(System.out::println);
//
//
//        // Busqueda de epusodios a partir de X año
//
///        System.out.println("Indica el a partir el cual deseas ver los episodios");
////        var fecha = scanner.nextInt();
////        scanner.nextLine();
////
////        LocalDate fechaBusqueda = LocalDate.of(fecha, 1,1);
////        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyyy");
////        episodios.stream()
////                .filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
////                .forEach(e -> System.out.println(
////                        "Temporada" + e.getTemporada() +
////                                "Episodio" + e.getNumeroEpisodio()+
////                                "Fecha de lanzamiento" + e.getFechaDeLanzamiento().format(dateTimeFormatter)
////                ));
//        // Busqueda por pedazo de titulo
//
////        System.out.println("Ingresa la palabra clave de busqueda");
////        var pedazoDeBusqueda = scanner.nextLine();
////        Optional<Episodio> episodioBuscado = episodios.stream()
////                .filter(e -> e.getTitulo().toLowerCase().contains(pedazoDeBusqueda.toLowerCase()))
////                .findFirst();
////
////        if (episodioBuscado.isPresent()){
////            System.out.println("Episodio Encotrado !! ");
////            System.out.println("Los datos son: "+ episodioBuscado.get());
////        }else {
////            System.out.println("Episodio no encontrado");
////        }
//
//        // Evaluacion por total de evaluaciones
//
//        Map<Integer, Double> evaluacionesPorTemporada = episodios.stream()
//                .filter(e->e.getEvaluacion()>0.0)
//                .collect(Collectors.groupingBy(Episodio::getTemporada,
//                        Collectors.averagingDouble(Episodio::getEvaluacion)));
//        System.out.println(evaluacionesPorTemporada);
//
//        DoubleSummaryStatistics est = episodios.stream()
//                .filter(e ->e.getEvaluacion()>0.0)
//                .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
//        System.out.println(est);
//        System.out.println("Episodio mejor evaluado: " + est.getMax());
//        System.out.println("Episodio peor evaluado: " + est.getMin());
//        System.out.println("La Evaluacion media de todos los capitulos de la serie serian: "+est.getAverage());
//
//
//
//
//
//    }

// forma para borrar la base de datos spring.jpa.hibernate.ddl-auto=update