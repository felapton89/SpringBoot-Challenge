package com.alkemychallenge.controller;

import com.alkemychallenge.model.dto.PeliculaDTO;
import com.alkemychallenge.model.dto.PeliculaDTOconPersonajes;
import com.alkemychallenge.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class PeliculaController {

    @Autowired
    private PeliculaService service;

    @GetMapping
    public List<PeliculaDTO> getMovies(@RequestParam(name = "name", required = false) String titulo,
                                       @RequestParam(name = "order", required = false) String order,
                                       @RequestParam(name = "genre", required = false) Long idGenero) {
        return service.listOfMovies(titulo, order, idGenero);
    }

    @PostMapping("/save")
    public void save(@RequestBody PeliculaDTO dto,
                     @RequestParam(name = "id_genero", required = false) Long idGenero) {
        service.save(dto, idGenero);
    }

    @PutMapping("/edit")
    public void editMovie(@RequestBody PeliculaDTO dto,
                          @RequestParam(name = "id_pelicula") Long idPelicula,
                          @RequestParam(name = "id_genero", required = false) Long idGenero) {
        service.update(dto, idPelicula, idGenero);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/details/{id}")
    public PeliculaDTOconPersonajes movieDetails(@PathVariable Long id) {
        return service.movieDetail(id);
    }



}
