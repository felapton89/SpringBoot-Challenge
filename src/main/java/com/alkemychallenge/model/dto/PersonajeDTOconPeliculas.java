package com.alkemychallenge.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonajeDTOconPeliculas {
    private String imagen;
    private String nombre;
    private String edad;
    private String peso;
    private String historia;
    private List<PeliculaDTO> peliculas;
}
