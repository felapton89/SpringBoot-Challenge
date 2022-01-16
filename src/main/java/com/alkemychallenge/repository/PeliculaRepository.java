package com.alkemychallenge.repository;

import com.alkemychallenge.model.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    List<Pelicula> findByTitulo(String titulo);

    @Query(nativeQuery = true,
            value = "SELECT * FROM peliculas ORDER BY fecha_creacion ASC")
    List<Pelicula> moviesOrderASC();

    @Query(nativeQuery = true,
            value = "SELECT * FROM peliculas ORDER BY fecha_creacion DESC")
    List<Pelicula> moviesOrderDESC();

    List<Pelicula> findByTituloOrderByFechaCreacionAsc(String titulo);

    List<Pelicula> findByTituloOrderByFechaCreacionDesc(String titulo);

    @Query(nativeQuery = true,
            value = "SELECT * FROM peliculas WHERE genero_id = :id")
    List<Pelicula> findByIdGenero(@Param("id") Long idGenero);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "DELETE FROM personajes_peliculas WHERE id_pelicula = :id")
    void deleteIntermediateTable(@Param("id") Long id);

}
