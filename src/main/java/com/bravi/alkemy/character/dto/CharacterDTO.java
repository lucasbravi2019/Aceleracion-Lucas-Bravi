package com.bravi.alkemy.character.dto;

import com.bravi.alkemy.generic.IGenericDTO;
import com.bravi.alkemy.generic.RecordType;
import com.bravi.alkemy.movie.dto.MovieDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CharacterDTO implements IGenericDTO {

    private Long id;

    @NotEmpty(message = "is required")
    private String name;

    @NotEmpty(message = "is required")
    private String image;

    @NotNull(message = "is required")
    @Min(value = 0, message = "minimum value must be 0 or above")
    private Byte age;

    @NotNull(message = "is required")
    @Min(value = 0, message = "minimum value must be 0 or above")
    private Float weight;

    @NotEmpty(message = "is required")
    private String history;

    private List<MovieDTO> movies;

    public CharacterDTO(Long id, String name, String image, Byte age, Float weight, String history, List<MovieDTO> movies) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.age = age;
        this.weight = weight;
        this.history = history;
        this.movies = movies;
    }

    @Override
    public Record generateRecord(RecordType recordType) {
        if (recordType == RecordType.BASIC) {
            return new CharacterBasicRecord(name,  image);
        } else if (recordType == RecordType.DETAILED) {
            return new CharacterDetailedRecord(
                    id,
                    name,
                    image,
                    age,
                    weight,
                    history,
                    movies != null ?
                            movies
                                    .stream()
                                    .map(movie -> movie.generateRecord(RecordType.DETAILED_WITHOUT_RELATIONSHIP))
                                    .toList() :
                            null
            );
        } else if (recordType == RecordType.DETAILED_WITHOUT_RELATIONSHIP) {
            return new CharacterDetailedWithNoMoviesRecord(
                    id,
                    name,
                    image,
                    age,
                    weight,
                    history
            );
        } else {
            return null;
        }
    }
}
