package com.alkemychallenge.controller;

import com.alkemychallenge.model.dto.ListadoPersonajeDTO;
import com.alkemychallenge.model.dto.PersonajeDTOconPeliculas;
import com.alkemychallenge.model.dto.PersonajeSave;
import com.alkemychallenge.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired
    private PersonajeService service;

    @GetMapping()
    public List<ListadoPersonajeDTO> getCharacters(@RequestParam(value = "name", required = false) String nombre,
                                                   @RequestParam(value = "age", required = false) String edad,
                                                   @RequestParam(value = "weight", required = false) String peso,
                                                   @RequestParam(value = "movies", required = false) Long idPelicula) {
        return service.listOfCharacters(nombre, edad, peso, idPelicula);
    }

    @PostMapping("/save")
    public void saveCharacter(@RequestBody PersonajeSave personaje) {
        service.save(personaje);
    }

    @PutMapping("/edit")
    public void editCharacter(@RequestBody PersonajeSave personaje,
                              @RequestParam(name = "id_personaje") Long idPersonaje) {
        service.update(personaje, idPersonaje);
    }

    @PostMapping("/add/relationship/{idPersonaje}/{idPelicula}")
    public void addRelationship(@PathVariable Long idPersonaje,
                                @PathVariable Long idPelicula) {
        service.addRelationship(idPersonaje, idPelicula);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCharacter(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/details/{id}")
    public PersonajeDTOconPeliculas characterDetail(@PathVariable Long id) {
        return service.characterDetail(id);
    }





}
