package com.alkemychallenge.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "generos")
@Data
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    private long id;
    private String nombre;
    private String imagen;

    @JsonBackReference
    @OneToMany(mappedBy = "genero")
    private List<Pelicula> peliculas;
}
