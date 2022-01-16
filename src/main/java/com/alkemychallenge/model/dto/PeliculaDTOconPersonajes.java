package com.alkemychallenge.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PeliculaDTOconPersonajes {
    private String imagen;
    private String titulo;
    private LocalDate fechaCreacion;
    private int calificacion;
    private List<ListadoPersonajeDTO> personajes;
    private GeneroDTO genero;

}
