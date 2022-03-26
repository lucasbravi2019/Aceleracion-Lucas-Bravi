package com.bravi.alkemy.character.dto;

import com.bravi.alkemy.movie.dto.MovieDTO;

import java.util.List;

public class CharacterDTOBuilder {

    private Long id;
    private String name;
    private String image;
    private Byte age;
    private Float weight;
    private String history;
    private List<MovieDTO> movies;

    public CharacterDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public CharacterDTOBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CharacterDTOBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public CharacterDTOBuilder setAge(Byte age) {
        this.age = age;
        return this;
    }

    public CharacterDTOBuilder setWeight(Float weight) {
        this.weight = weight;
        return this;
    }

    public CharacterDTOBuilder setHistory(String history) {
        this.history = history;
        return this;
    }

    public CharacterDTOBuilder setMovies(List<MovieDTO> movies) {
        this.movies = movies;
        return this;
    }

    public CharacterDTO build() {
        return new CharacterDTO(
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