package com.alkemychallenge.service;

import com.alkemychallenge.model.dto.ListadoPersonajeDTO;
import com.alkemychallenge.model.dto.PersonajeDTOconPeliculas;
import com.alkemychallenge.model.dto.PersonajeSave;

import java.util.List;

public interface PersonajeService {

    void save(PersonajeSave personaje);

    void update(PersonajeSave personaje, Long idPersonaje);

    void delete(Long id);

    List<ListadoPersonajeDTO> listOfCharacters(String nombre, String edad, String peso, Long idPelicula);

    PersonajeDTOconPeliculas characterDetail(Long id);

    void addRelationship(Long idPersonaje, Long idPelicula);

}
