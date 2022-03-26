package com.bravi.alkemy.gender.dto;

public record GenderDetailedRecord(Long id, String genderName, String image) {

    public GenderDetailedRecord(Long id, String genderName, String image) {
        this.id = id;
        this.genderName = genderName;
        this.image = image;
    }

}
