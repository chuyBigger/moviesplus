package com.aluracursos.moviesplus.modelos;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "episodios")

public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double evaluacion;
    private LocalDate fechaDeLanzamiento;
    @ManyToOne
    private Serie serie;

    public Episodio(){}


    public Episodio(Integer numero, DatosEpisodio d) {
        this.temporada = numero;
        this.titulo = d.titulo();
        this.numeroEpisodio = d.numeroEpisodio();
        try {
            this.evaluacion = Double.valueOf(d.evaluacion());
        }catch (NumberFormatException e){
            this.evaluacion = 0.0;
        }
        try {
            this.fechaDeLanzamiento = LocalDate.parse(d.fechaDeLanzamiento());
        }catch (DateTimeParseException e){
            this.fechaDeLanzamiento = LocalDate.ofEpochDay(12/12/12);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    @Override
    public String toString() {
        return "Episodio: " +
                " Temporada: " + temporada +
                " Titulo:" + titulo + '\'' +
                " NumeroEpisodio: " + numeroEpisodio +
                " Evaluacion: " + evaluacion +
                " FechaDeLanzamiento: " + fechaDeLanzamiento;
    }
}
