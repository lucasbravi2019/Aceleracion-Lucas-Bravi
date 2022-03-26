package com.bravi.alkemy.movie.dto;

import com.bravi.alkemy.character.dto.CharacterDTO;
import com.bravi.alkemy.gender.dto.GenderDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MovieDTOBuilder {

    private Long id;
    private String title;
    private String image;
    private List<CharacterDTO> characters;
    private GenderDTO gender;
    private LocalDate createdAt;

    public MovieDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public MovieDTOBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public MovieDTOBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public MovieDTOBuilder setCharacters(List<CharacterDTO> characters) {
        this.characters = characters;
        return this;
    }

    public MovieDTOBuilder setGender(GenderDTO gender) {
        this.gender = gender;
        return this;
    }

    public MovieDTOBuilder setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public MovieDTO build() {
        return new MovieDTO(
                id,
                title,
                image,
                characters,
                gender,
                createdAt
        );
    }
}