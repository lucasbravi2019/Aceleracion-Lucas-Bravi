package com.bravi.alkemy.character.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterFilterDTO {

    private String name;
    private Byte age;
    private Float weight;
    private List<Long> moviesId;

    public CharacterFilterDTO(String name, Byte age, Float weight, List<Long> moviesId) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.moviesId = moviesId;
    }

}
