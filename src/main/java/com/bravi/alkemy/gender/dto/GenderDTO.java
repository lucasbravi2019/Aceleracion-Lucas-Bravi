package com.bravi.alkemy.gender.dto;

import com.bravi.alkemy.generic.IGenericDTO;
import com.bravi.alkemy.generic.RecordType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class GenderDTO implements IGenericDTO {

    private Long id;

    @NotEmpty(message = "is required")
    private String name;

    @NotEmpty(message = "is required")
    private String image;

    public GenderDTO(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    @Override
    public Record generateRecord(RecordType recordType) {
        if (recordType == RecordType.BASIC) {
            return new GenderBasicRecord(name, image);
        } else if (recordType == RecordType.DETAILED) {
            return new GenderDetailedRecord(id, name, image);
        } else {
            return null;
        }
    }

}
