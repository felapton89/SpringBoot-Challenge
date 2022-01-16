package com.alkemychallenge.model.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "peliculas")
@Data
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula")
    private long id;
    private String imagen;
    @NotBlank(message = "Ingrese título de película")
    private String titulo;
    private LocalDate fechaCreacion;
    @Min(value = 1)
    @Max(value = 5)
    private int calificacion;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "peliculas")
    private List<Personaje> personajes;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genero_id")
    private Genero genero;
}
