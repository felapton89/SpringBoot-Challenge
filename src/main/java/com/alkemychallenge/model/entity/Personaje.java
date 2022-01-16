package com.alkemychallenge.model.entity;

import com.alkemychallenge.model.dto.PeliculaDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "personajes")
@Data
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personaje")
    private long id;
    private String imagen;
    @NotBlank(message = "Nombre del personaje requerido")
    private String nombre;
    private String edad;
    private String peso;
    @NotBlank(message = "Todo personaje tiene que tener una historia")
    private String historia;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "personajes_peliculas",
            joinColumns = {
                    @JoinColumn(name = "id_personaje", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_pelicula", nullable = false)})
    private List<Pelicula> peliculas;
}
