package com.aluracursos.moviesplus.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSerie(
        @JsonAlias("Title") String titulo,
        @JsonAlias("totalSeasons") Integer totalDeTemporadas,
        @JsonAlias("imdbRating") String evaluacion,
        @JsonAlias("Poster") String poster,
        @JsonAlias("Plot") String sinopsys,
        @JsonAlias("Genre") String genero,
        @JsonAlias("Actors") String actores
) {

}
