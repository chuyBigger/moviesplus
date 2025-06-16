package com.aluracursos.moviesplus.repositorio;

import com.aluracursos.moviesplus.modelos.Categoria;
import com.aluracursos.moviesplus.modelos.Episodio;
import com.aluracursos.moviesplus.modelos.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    Optional<Serie> findByTituloContainsIgnoreCase(String serieBuscar);

    List<Serie> findTop5ByOrderByEvaluacionDesc();

    List<Serie> findByGenero(Categoria categoria);

    //List<Serie> findByTotalDeTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(int totalDeTemporadas, double evaluaciones);
    //Trabajando native query directo la DB @Query(value = "SELECT * FROM series WHERE series.total_de_temporadas <= 6 AND series.evaluacion >= 7.8", nativeQuery = true)
    @Query(value = "SELECT s FROM Serie s WHERE s.totalDeTemporadas <= :totalDeTemporadas AND s.evaluacion >= :evaluacion")
    List<Serie> findByTotalDeTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(int totalDeTemporadas, double evaluacion);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:nombreDeEpisodio%")
    List<Episodio> episodiosPorNombre(String nombreDeEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.evaluacion DESC LIMIT 5 ")
    List<Episodio> top5episodios(Serie serie);
}
