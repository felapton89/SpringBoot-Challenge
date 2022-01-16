package com.alkemychallenge.service.impl;

import com.alkemychallenge.model.dto.ListadoPersonajeDTO;
import com.alkemychallenge.model.dto.PersonajeDTOconPeliculas;
import com.alkemychallenge.model.dto.PersonajeSave;
import com.alkemychallenge.model.entity.Personaje;
import com.alkemychallenge.repository.PeliculaRepository;
import com.alkemychallenge.repository.PersonajeRepository;
import com.alkemychallenge.service.PersonajeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private ModelMapper mapper;


    @Override
    public void save(PersonajeSave personaje) {
        Personaje newCharacter = mapper.map(personaje, Personaje.class);
        personajeRepository.save(newCharacter);
    }

    @Override
    public void update(PersonajeSave personaje, Long idPersonaje) {
        Personaje update = personajeRepository.findById(idPersonaje)
                .orElseThrow(
                        () -> new IllegalStateException("Personaje con id: " + idPersonaje + " no existe.")
                );
        update.setImagen(personaje.getImagen());
        update.setNombre(personaje.getNombre());
        update.setEdad(personaje.getEdad());
        update.setPeso(personaje.getPeso());
        update.setHistoria(personaje.getHistoria());
        personajeRepository.save(update);
    }

    @Override
    public void delete(Long id) {
        personajeRepository.deleteIntermediateTable(id);
        personajeRepository.deleteById(id);
    }


    @Override
    public PersonajeDTOconPeliculas characterDetail(Long id) {
        Personaje entity = personajeRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Personaje con id: " + id + " no existe.")
                );
        return mapper.map(entity, PersonajeDTOconPeliculas.class);
    }

    @Override
    public void addRelationship(Long idPersonaje, Long idPelicula) {
        if (personajeRepository.existsById(idPersonaje) && peliculaRepository.existsById(idPelicula)) {
            personajeRepository.addRelationship(idPersonaje, idPelicula);
        }
    }

    @Override
    public List<ListadoPersonajeDTO> listOfCharacters(String nombre, String edad, String peso, Long idPelicula) {

        if (nombre != null) {
            List<Personaje> entity = personajeRepository.findByNombre(nombre);
            if (entity.isEmpty()) {
                throw new IllegalStateException("No existe personaje con ese nombre");
            }
            return listPersonajeEntityToDTO(entity);
        }
        if (edad != null) {
            List<Personaje> entity = personajeRepository.findByEdad(edad);
            if (entity.isEmpty()) {
                throw new IllegalStateException("No existe personaje con esa edad");
            }
            return listPersonajeEntityToDTO(entity);
        }
        if (peso != null) {
            List<Personaje> entity = personajeRepository.findByPeso(peso);
            if (entity.isEmpty()) {
                throw new IllegalStateException("No existe personaje con ese peso");
            }
            return listPersonajeEntityToDTO(entity);
        }
        if (idPelicula != null) {
            List<Personaje> entity = personajeRepository.findPersoajesByPeliculaId(idPelicula);
            if (entity.isEmpty()) {
                throw new IllegalStateException("No existe personaje con ese ID de película");
            }
            return listPersonajeEntityToDTO(entity);
        }

        return listPersonajeEntityToDTO(personajeRepository.findAll());
    }


    private List<ListadoPersonajeDTO> listPersonajeEntityToDTO(List<Personaje> personajesEntity) {
        List<ListadoPersonajeDTO> listadoPersonajeDTOS = new ArrayList<>();
        for (Personaje entity : personajesEntity) {
            ListadoPersonajeDTO dto = mapper.map(entity, ListadoPersonajeDTO.class);
            listadoPersonajeDTOS.add(dto);
        }
        return listadoPersonajeDTOS;
    }

}
