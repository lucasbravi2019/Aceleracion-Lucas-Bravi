package com.bravi.alkemy.gender.dto;

public class GenderDTOBuilder {

    private Long id;
    private String name;
    private String image;

    public GenderDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public GenderDTOBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public GenderDTOBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public GenderDTO build() {
        return new GenderDTO(id, name, image);
    }
}