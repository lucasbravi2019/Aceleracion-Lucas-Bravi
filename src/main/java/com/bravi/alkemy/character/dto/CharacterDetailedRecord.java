package com.bravi.alkemy.character.dto;

import java.util.List;

public record CharacterDetailedRecord(
        Long id,
        String name,
        String image,
        Byte age,
        Float weight,
        String history,
        List<Record> movies
) {

}
