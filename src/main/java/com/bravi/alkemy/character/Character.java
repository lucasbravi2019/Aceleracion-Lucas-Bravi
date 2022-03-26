package com.bravi.alkemy.character;

import com.bravi.alkemy.movie.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "characters")
@Where(clause = "deleted = false")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String image;
    private Byte age;
    private Float weight;
    private String history;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "characters")
    private List<Movie> movies;

    public Character(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Character(Long id, String name, String image, Byte age, Float weight, String history) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.age = age;
        this.weight = weight;
        this.history = history;
    }

    public Character(Long id, String name, String image, Byte age, Float weight, String history, List<Movie> movies) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.age = age;
        this.weight = weight;
        this.history = history;
        this.movies = movies;
    }
}
