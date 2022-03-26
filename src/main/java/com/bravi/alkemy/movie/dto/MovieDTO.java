package com.bravi.alkemy.movie.dto;

import com.bravi.alkemy.character.dto.CharacterDTO;
import com.bravi.alkemy.gender.dto.GenderDTO;
import com.bravi.alkemy.generic.IGenericDTO;
import com.bravi.alkemy.generic.RecordType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MovieDTO implements IGenericDTO {

    private Long id;

    @NotEmpty(message = "is required")
    private String title;

    @NotEmpty(message = "is required")
    private String image;

    private List<CharacterDTO> characters;
    private GenderDTO gender;
    private LocalDate createdAt;

    @Min(value = 1, message = "Score must be from 1 to 5")
    @Max(value = 5, message = "Score must be from 1 to 5")
    private Byte score;

    public MovieDTO(Long id, String title, String image, List<CharacterDTO> characters, GenderDTO gender, LocalDate createdAt) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.characters = characters;
        this.gender = gender;
        this.createdAt = createdAt;
    }

    @Override
    public Record generateRecord(RecordType recordType) {
        if (recordType == RecordType.BASIC) {
            return new MovieBasicRecord(title,image,createdAt);
        } else if (recordType == RecordType.DETAILED) {

            return new MovieDetailedRecord(
                    id,
                    title,
                    image,
                    createdAt,
                    characters != null ?
                            characters
                                    .stream()
                                    .map(character -> character.generateRecord(RecordType.DETAILED_WITHOUT_RELATIONSHIP))
                                    .collect(Collectors.toList()) :
                            null,
                    gender != null ?
                            gender.generateRecord(RecordType.DETAILED) :
                            null
            );
        } else if (recordType == RecordType.DETAILED_WITHOUT_RELATIONSHIP) {
            return new MovieDetailedWithNoCharactersRecord(
                    id,
                    title,
                    image,
                    createdAt,
                    gender != null ?
                            gender.generateRecord(RecordType.DETAILED) :
                            null
            );
        } else {
            return null;
        }
    }
}
