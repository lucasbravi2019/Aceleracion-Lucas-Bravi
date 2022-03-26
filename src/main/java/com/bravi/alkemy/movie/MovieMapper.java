package com.bravi.alkemy.movie;

import com.bravi.alkemy.character.Character;
import com.bravi.alkemy.character.CharacterMapper;
import com.bravi.alkemy.gender.GenderMapper;
import com.bravi.alkemy.generic.IGenericMapper;
import com.bravi.alkemy.movie.dto.MovieDTO;
import com.bravi.alkemy.movie.dto.MovieDTOBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper implements IGenericMapper<Movie, MovieDTO> {

    @Override
    public Movie toEntity(MovieDTO dto) {
        GenderMapper genderMapper = new GenderMapper();
        CharacterMapper characterMapper = new CharacterMapper();
        return new MovieBuilder()
                .setImage(dto.getImage())
                .setTitle(dto.getTitle())
                .setCharacters(
                        dto.getCharacters() != null ?
                                dto.getCharacters()
                                        .stream()
                                        .map(characterMapper::toEntity)
                                        .collect(Collectors.toList()) :
                                null
                )
                .setGender(
                        dto.getGender() != null ?
                                genderMapper.toEntity(dto.getGender()) :
                                null
                )
                .build();
    }

    @Override
    public MovieDTO toDTO(Movie entity) {
        CharacterMapper characterMapper = new CharacterMapper();
        GenderMapper genderMapper = new GenderMapper();
        return new MovieDTOBuilder()
                .setId(entity.getId())
                .setTitle(entity.getTitle())
                .setImage(entity.getImage())
                .setCreatedAt(entity.getCreatedAt())
                .setCharacters(
                        entity.getCharacters() != null ?
                                characterMapper.toDTOList(
                                        entity.getCharacters()
                                                .stream()
                                                .map(character -> {
                                                    character.setMovies(null);
                                                    return character;
                                                })
                                                .collect(Collectors.toList())) :
                                null
                )
                .setGender(
                        entity.getGender() != null ?
                                genderMapper.toDTO(entity.getGender()) :
                                null
                )
                .build();
    }

    @Override
    public List<MovieDTO> toDTOList(List<Movie> list) {
        return list
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
