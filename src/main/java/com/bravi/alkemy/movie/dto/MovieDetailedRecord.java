package com.bravi.alkemy.movie.dto;

import java.time.LocalDate;
import java.util.List;

public record MovieDetailedRecord(
        Long id,
        String title,
        String image,
        LocalDate createdAt,
        List<Record> characters,
        Record gender
) {

}
