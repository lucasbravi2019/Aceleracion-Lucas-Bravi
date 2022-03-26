package com.bravi.alkemy.character;

import com.bravi.alkemy.character.dto.CharacterDTO;
import com.bravi.alkemy.movie.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {

    @InjectMocks
    CharacterMapper characterMapper;

    @Mock
    CharacterRepository characterRepository;

    List<Character> characters = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Character c1 = new Character(1L, "Character 1", "Image 1", (byte) 15, Double.valueOf(23.2).floatValue(), "History 1", List.of(new Movie()));
        Character c2 = new Character(2L, "Character 2", "Image 2", (byte) 17, Double.valueOf(25.6).floatValue(), "History 2", List.of(new Movie()));

        characters.add(c1);
        characters.add(c2);
    }

    @Test
    void fetchAllCharacters() {
        when(characterRepository.findAll()).thenReturn(characters);

        assertNotNull(characters);
        assertNotNull(characterMapper);
        assertNotNull(characterRepository);
        assertEquals(2, characters.size());

        List<Character> allCharacters = characterRepository.findAll();

        verify(characterRepository, times(1)).findAll();

        assertEquals(2, allCharacters.size());

        Character firstCharacter = allCharacters.get(0);
        assertEquals(1L, firstCharacter.getId());
        assertEquals("Character 1", firstCharacter.getName());
        assertEquals("Image 1", firstCharacter.getImage());
        assertEquals((byte) 15, firstCharacter.getAge());
        assertEquals(Double.valueOf(23.2).floatValue(), firstCharacter.getWeight());
        assertEquals("History 1", firstCharacter.getHistory());
        assertEquals(1, firstCharacter.getMovies().size());

        assertInstanceOf(ArrayList.class, allCharacters);
        assertInstanceOf(Character.class, firstCharacter);
        assertInstanceOf(CharacterDTO.class, characterMapper.toDTO(firstCharacter));
        assertNotNull(characterMapper.toDTOList(characters));

        CharacterDTO firstDTO = characterMapper.toDTOList(characters).get(0);
        assertEquals(1L, firstDTO.getId());
        assertEquals("Character 1", firstDTO.getName());
        assertEquals("Image 1", firstDTO.getImage());
        assertEquals((byte) 15, firstDTO.getAge());
        assertEquals(Double.valueOf(23.2).floatValue(), firstDTO.getWeight());
        assertEquals("History 1", firstDTO.getHistory());
        assertEquals(1, firstDTO.getMovies().size());


        CharacterDTO dto = characterMapper.toDTO(firstCharacter);

        assertEquals(1L, dto.getId());
        assertEquals("Character 1", dto.getName());
        assertEquals("Image 1", dto.getImage());
        assertEquals((byte) 15, dto.getAge());
        assertEquals(Double.valueOf(23.2).floatValue(), dto.getWeight());
        assertEquals("History 1", dto.getHistory());
        assertEquals(1, dto.getMovies().size());
    }

    @Test
    void fetchCharacterById() {
        when(characterRepository.findById(1L)).thenReturn(Optional.of(characters.get(0)));
        Character character = characterRepository.findById(1L).get();

        assertNotNull(character);
        assertInstanceOf(Character.class,character);

        assertEquals(1L, character.getId());
        assertEquals("Character 1", character.getName());
        assertEquals("Image 1", character.getImage());
        assertEquals((byte) 15, character.getAge());
        assertEquals(Double.valueOf(23.2).floatValue(), character.getWeight());
        assertEquals("History 1", character.getHistory());
        assertEquals(1, character.getMovies().size());

        verify(characterRepository, times(1)).findById(1L);
    }

    @Test
    void saveCharacter() {
        Character character = new Character(
                null,
                "New character",
                "New Image",
                (byte) 32,
                Double.valueOf(42.32).floatValue(),
                "New history",
                List.of(new Movie())
        );

        Character characterSaved = character;
        characterSaved.setId(1L);

        when(characterRepository.save(character)).thenReturn(characterSaved);

        Character newCharacter = characterRepository.save(character);

        verify(characterRepository, times(1)).save(character);

        assertEquals(1L, newCharacter.getId());
        assertEquals("New character", newCharacter.getName());
        assertEquals("New Image", newCharacter.getImage());
        assertEquals((byte) 32, newCharacter.getAge());
        assertEquals(Double.valueOf(42.32).floatValue(), newCharacter.getWeight());
        assertEquals("New history", newCharacter.getHistory());
        assertEquals(1, newCharacter.getMovies().size());
        assertInstanceOf(Movie.class, newCharacter.getMovies().get(0));

    }

    @Test
    void updateCharacter() {
        Character character = new Character(
                1L,
                "New character",
                "New Image",
                (byte) 32,
                Double.valueOf(42.32).floatValue(),
                "New history",
                List.of(new Movie())
        );

        when(characterRepository.save(character)).thenReturn(character);

        Movie movie = new Movie("new movie", "new image", LocalDate.now());
        character.setMovies(List.of(movie));

        Character savedCharacter = characterRepository.save(character);

        verify(characterRepository, times(1)).save(character);
        assertEquals(character.getMovies(), savedCharacter.getMovies());
    }

    @Test
    void deleteCharacter() {
        when(characterRepository.delete(1L)).thenReturn(List.of(characters.remove(0)).size());

        int charactersRemoved = characterRepository.delete(1L);
        assertEquals(1, charactersRemoved);

        verify(characterRepository, times(1)).delete(1L);
    }
}