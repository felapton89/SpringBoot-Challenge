package com.alkemychallenge.service.impl;

import com.alkemychallenge.model.dto.PeliculaDTO;
import com.alkemychallenge.model.dto.PeliculaDTOconPersonajes;
import com.alkemychallenge.model.entity.Genero;
import com.alkemychallenge.model.entity.Pelicula;
import com.alkemychallenge.repository.GeneroRepository;
import com.alkemychallenge.repository.PeliculaRepository;
import com.alkemychallenge.repository.PersonajeRepository;
import com.alkemychallenge.service.PeliculaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PeliculaRepository repository;

    @Autowired
    PersonajeRepository personajeRepository;

    @Autowired
    GeneroRepository generoRepository;


    @Override
    public List<PeliculaDTO> listOfMovies(String titulo, String order, Long idGenero) {
        if (titulo != null && order == null) {
            List<Pelicula> entity = repository.findByTitulo(titulo);
            if (entity.isEmpty()) {
                throw new IllegalStateException("No hay películas registradas con ese título");
            }
            return listPeliculaEntityToDTO(entity);
        }
        if (order != null && titulo == null) {
            if (order.equalsIgnoreCase("asc")) {
                return listPeliculaEntityToDTO(repository.moviesOrderASC());
            } else {
                return listPeliculaEntityToDTO(repository.moviesOrderDESC());
            }
        }
        if (titulo != null && order != null) {
            if (order.equalsIgnoreCase("asc")) {
                List<Pelicula> entity = repository.findByTituloOrderByFechaCreacionAsc(titulo);
                if (entity.isEmpty()) {
                    throw new IllegalStateException("No hay películas registradas con ese título");
                }
                return listPeliculaEntityToDTO(entity);
            } else {
                List<Pelicula> entity = repository.findByTituloOrderByFechaCreacionDesc(titulo);
                if (entity.isEmpty()) {
                    throw new IllegalStateException("No hay películas registradas con ese título");
                }
                return listPeliculaEntityToDTO(entity);
            }
        }
        if (idGenero != null) {
            List<Pelicula> entity = repository.findByIdGenero(idGenero);
            if (entity.isEmpty()) {
                throw new IllegalStateException("No hay películas registradas con ese ID género");
            }
            return listPeliculaEntityToDTO(entity);
        }
        return listPeliculaEntityToDTO(repository.findAll());
    }

    @Override
    public void save(PeliculaDTO dto, Long idGenero) {
        Pelicula nueva = mapper.map(dto, Pelicula.class);
        if (idGenero != null) {
            Optional<Genero> optionalGenero = generoRepository.findById(idGenero);
            if (optionalGenero.isPresent()) {
                nueva.setGenero(optionalGenero.get());
            }
        }
        repository.save(nueva);

    }

    @Override
    public void update(PeliculaDTO dto, Long idPelicula, Long idGenero) {
        Pelicula pelicula = repository.findById(idPelicula)
                .orElseThrow(
                        () -> new IllegalStateException("Pelicula con id: " + idPelicula + " no existe.")
                );
        pelicula.setImagen(dto.getImagen());
        pelicula.setTitulo(dto.getTitulo());
        pelicula.setFechaCreacion(dto.getFechaCreacion());
        pelicula.setCalificacion(dto.getCalificacion());
        if (idGenero != null) {
            Optional<Genero> optionalGenero = generoRepository.findById(idGenero);
            optionalGenero.ifPresent(pelicula::setGenero);
        }
        repository.save(pelicula);
    }

    @Override
    public void delete(Long id) {
        repository.deleteIntermediateTable(id);
        repository.deleteById(id);
    }

    @Override
    public PeliculaDTOconPersonajes movieDetail(Long id) {
        Pelicula entity = repository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Pelicula con id: " + id + " no existe.")
                );
        return mapper.map(entity, PeliculaDTOconPersonajes.class);

    }


    public List<PeliculaDTO> listPeliculaEntityToDTO(List<Pelicula> peliculaEntity) {
        List<PeliculaDTO> peliculaDTOS = new ArrayList<>();
        for (Pelicula entity : peliculaEntity) {
            PeliculaDTO dto = mapper.map(entity, PeliculaDTO.class);
            peliculaDTOS.add(dto);
        }
        return peliculaDTOS;
    }
}
