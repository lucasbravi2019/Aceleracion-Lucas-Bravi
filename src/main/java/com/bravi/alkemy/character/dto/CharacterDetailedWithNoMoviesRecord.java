package com.bravi.alkemy.character.dto;

public record CharacterDetailedWithNoMoviesRecord(
        Long id,
        String name,
        String image,
        Byte age,
        Float weight,
        String history
) {
}
