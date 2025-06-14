package com.aluracursos.moviesplus.repositorio;

import com.aluracursos.moviesplus.modelos.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie,Long> {
}
