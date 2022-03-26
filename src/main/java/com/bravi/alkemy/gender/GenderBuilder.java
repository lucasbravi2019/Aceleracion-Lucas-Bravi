package com.bravi.alkemy.gender;

public class GenderBuilder {

    private Long id;
    private String name;
    private String image;

    public GenderBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public GenderBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public GenderBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public Gender build() {
        return new Gender(id, name, image);
    }
}