package com.alkemychallenge.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PeliculaDTO {
    private String imagen;
    private String titulo;
    private LocalDate fechaCreacion;
    private int calificacion;
}
