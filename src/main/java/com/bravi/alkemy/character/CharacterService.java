package com.bravi.alkemy.character;

import com.bravi.alkemy.character.dto.CharacterDTO;
import com.bravi.alkemy.character.dto.CharacterFilterDTO;
import com.bravi.alkemy.exception.CharacterNotFoundException;
import com.bravi.alkemy.generic.RecordType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterSpecification characterSpecification;

    public CharacterService(
            CharacterRepository characterRepository,
            CharacterMapper characterMapper,
            CharacterSpecification characterSpecification
    ) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
        this.characterSpecification = characterSpecification;
    }

    public List<Record> fetchAllCharacters(String name, Float weight, Byte age, List<Long> moviesId) {
        CharacterFilterDTO dto = new CharacterFilterDTO(name, age, weight, moviesId);
        return characterMapper.toDTOList(
                        characterRepository.findAll(
                                characterSpecification.getByFilters(dto)
                        )
                )
                .stream()
                .map(character -> character.generateRecord(RecordType.BASIC))
                .collect(Collectors.toList());
    }

    public Record fetchCharacterById(Long id) {
        return characterMapper.toDTO(
                characterRepository.findById(id)
                        .orElseThrow(() -> new CharacterNotFoundException(id))
                )
                .generateRecord(RecordType.DETAILED);
    }

    public Record saveCharacter(CharacterDTO dto) {
        return characterMapper.toDTO(
                        characterRepository.save(
                                characterMapper.toEntity(dto)
                        )
                )
                .generateRecord(RecordType.DETAILED);
    }

    public Record updateCharacter(Long id, CharacterDTO dto) {
        Character character = characterRepository.findById(id)
                .orElseThrow(
                        () -> new CharacterNotFoundException(id)
                );
        character.setName(dto.getName());
        character.setImage(dto.getImage());
        character.setAge(dto.getAge());
        character.setHistory(dto.getHistory());
        character.setWeight(dto.getWeight());
        return characterMapper.toDTO(
                        characterRepository.save(character)
                )
                .generateRecord(RecordType.DETAILED);
    }

    @Transactional
    public int deleteCharacter(Long id) {
        int moviesRemoved = characterRepository.removeMovieFromCharacter(id);
        int deletedRows = characterRepository.delete(id);
        if (deletedRows == 0) throw new CharacterNotFoundException(id);
        return deletedRows;
    }


}
