package com.bravi.alkemy.movie.dto;

import java.time.LocalDate;

public record MovieDetailedWithNoCharactersRecord(
        Long id,
        String title,
        String image,
        LocalDate createdAt,
        Record gender
) {
}
