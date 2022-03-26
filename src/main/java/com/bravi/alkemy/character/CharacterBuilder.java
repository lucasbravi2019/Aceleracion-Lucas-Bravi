package com.bravi.alkemy.character;

import com.bravi.alkemy.movie.Movie;

import java.util.List;

public class CharacterBuilder {

    private Long id;
    private String name;
    private String image;
    private Byte age;
    private Float weight;
    private String history;
    private List<Movie> movies;

    public CharacterBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public CharacterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public CharacterBuilder setAge(Byte age) {
        this.age = age;
        return this;
    }

    public CharacterBuilder setWeight(Float weight) {
        this.weight = weight;
        return this;
    }

    public CharacterBuilder setHistory(String history) {
        this.history = history;
        return this;
    }

    public CharacterBuilder setMovies(List<Movie> movies) {
        this.movies = movies;
        return this;
    }

    public Character build() {
        return new Character(
                id,
                name,
                image,
                age,
                weight,
                history,
                movies
        );
    }
}