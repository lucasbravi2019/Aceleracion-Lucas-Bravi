package com.bravi.alkemy.character;

import com.bravi.alkemy.character.dto.CharacterDTO;
import com.bravi.alkemy.character.dto.CharacterDTOBuilder;
import com.bravi.alkemy.generic.IGenericMapper;
import com.bravi.alkemy.movie.MovieMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CharacterMapper implements IGenericMapper<Character, CharacterDTO> {

    @Override
    public Character toEntity(CharacterDTO dto) {
        return new CharacterBuilder()
                .setName(dto.getName())
                .setImage(dto.getImage())
                .setHistory(dto.getHistory())
                .setAge(dto.getAge())
                .setWeight(dto.getWeight())
                .build();
    }

    @Override
    public CharacterDTO toDTO(Character entity) {
        MovieMapper movieMapper = new MovieMapper();
        return new CharacterDTOBuilder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setImage(entity.getImage())
                .setWeight(entity.getWeight())
                .setAge(entity.getAge())
                .setHistory(entity.getHistory())
                .setMovies(
                        entity.getMovies() != null ?
                                movieMapper.toDTOList(entity.getMovies()) :
                                null
                )
                .build();
    }

    @Override
    public List<CharacterDTO> toDTOList(List<Character> list) {
        return list
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
