package com.bravi.alkemy.movie;

import com.bravi.alkemy.character.Character;
import com.bravi.alkemy.gender.Gender;

import java.util.List;

public class MovieBuilder {

    private Long id;
    private String title;
    private String image;
    private List<Character> characters;
    private Gender gender;

    public MovieBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public MovieBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public MovieBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public MovieBuilder setCharacters(List<Character> characters) {
        this.characters = characters;
        return this;
    }

    public MovieBuilder setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Movie build() {
        return new Movie(id, title, image, characters, gender);
    }
}