package com.alkemychallenge.repository;

import com.alkemychallenge.model.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {

    List<Personaje> findByNombre(String nombre);

    List<Personaje> findByEdad(String edad);

    List<Personaje> findByPeso(String peso);

    @Query(
            value = "SELECT * FROM personajes p inner join personajes_peliculas c " +
                    "ON p.id_personaje = c.id_personaje " +
                    "WHERE c.id_pelicula = :idPelicula",
            nativeQuery = true)
    List<Personaje> findPersoajesByPeliculaId(@Param("idPelicula") Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "DELETE FROM personajes_peliculas WHERE id_personaje = :id")
    void deleteIntermediateTable(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "INSERT INTO personajes_peliculas (id_personaje, id_pelicula) VALUES (:id1, :id2)")
    void addRelationship(@Param("id1") Long idPersonaje, @Param("id2") Long idPelicula);
}


