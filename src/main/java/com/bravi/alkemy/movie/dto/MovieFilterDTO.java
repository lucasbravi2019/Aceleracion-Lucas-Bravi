package com.bravi.alkemy.movie.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MovieFilterDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String title;
    private Long genderId;
    private String order;

    public MovieFilterDTO(String title, Long genderId, String order) {
        this.title = title;
        this.genderId = genderId;
        this.order = order;
    }

    public Boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

}
