package com.bravi.alkemy.movie.dto;

import java.time.LocalDate;

public record MovieBasicRecord(
        String title,
        String image,
        LocalDate createdAt
) {
}
