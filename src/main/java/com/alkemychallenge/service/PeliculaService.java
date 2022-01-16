package com.alkemychallenge.service;

import com.alkemychallenge.model.dto.PeliculaDTO;
import com.alkemychallenge.model.dto.PeliculaDTOconPersonajes;

import java.util.List;

public interface PeliculaService {

    List<PeliculaDTO> listOfMovies(String titulo, String order, Long idGenero);

    void save(PeliculaDTO dto, Long idGenero);

    void update(PeliculaDTO pelicula, Long idPelicula, Long idGenero);

    void delete(Long id);

    PeliculaDTOconPersonajes movieDetail(Long id);


}
