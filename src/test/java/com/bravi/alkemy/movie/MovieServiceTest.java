package com.bravi.alkemy.movie;

import com.bravi.alkemy.character.Character;
import com.bravi.alkemy.character.CharacterRepository;
import com.bravi.alkemy.gender.Gender;
import com.bravi.alkemy.movie.dto.MovieDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    MovieRepository movieRepository;

    @Mock
    CharacterRepository characterRepository;

    @InjectMocks
    MovieMapper movieMapper;

    List<Movie> movies = new ArrayList<>();

    @BeforeEach
    void setUp() {
        movies.add(new Movie(
                1L,
                "Movie 1",
                "Image 1",
                new ArrayList<>(List.of(new Character())),
                new Gender("Accion", "Image"))
        );
        movies.add(new Movie(
                2L,
                "Movie 2",
                "Image 2",
                new ArrayList<>(List.of(new Character())),
                new Gender("Comedia", "Image 2"))
        );
    }

    @Test
    void fetchAllMovies() {
        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> moviesFetched = movieRepository.findAll();

        verify(movieRepository, times(1)).findAll();

        Movie firstMovie = moviesFetched.get(0);
        assertEquals(1L, firstMovie.getId());
        assertEquals("Movie 1", firstMovie.getTitle());
        assertEquals("Image 1", firstMovie.getImage());
        assertEquals(1, firstMovie.getCharacters().size());
        assertEquals("Accion", firstMovie.getGender().getName());
        assertEquals("Image", firstMovie.getGender().getImage());

        assertInstanceOf(MovieDTO.class, movieMapper.toDTO(firstMovie));

        MovieDTO dto = movieMapper.toDTO(firstMovie);
        assertEquals(1L, dto.getId());
        assertEquals("Movie 1", dto.getTitle());
        assertEquals("Image 1", dto.getImage());
        assertEquals(1, dto.getCharacters().size());
        assertEquals("Accion", dto.getGender().getName());
        assertEquals("Image", dto.getGender().getImage());

        assertInstanceOf(ArrayList.class, movieMapper.toDTOList(moviesFetched));
        assertEquals(2, movieMapper.toDTOList(moviesFetched).size());
    }

    @Test
    void fetchMovieById() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movies.get(0)));

        Movie movie = movieRepository.findById(1L).get();

        verify(movieRepository, times(1)).findById(1L);
        assertNotNull(movie);

        assertEquals(1L, movie.getId());
        assertEquals("Movie 1", movie.getTitle());
        assertEquals("Image 1", movie.getImage());
        assertEquals(1, movie.getCharacters().size());
        assertEquals("Accion", movie.getGender().getName());
        assertEquals("Image", movie.getGender().getImage());
    }

    @Test
    void saveMovie() {
        Movie movie = new Movie(
                null,
                "new title",
                "new image",
                List.of(new Character()),
                new Gender("gender name", "gender image")
        );

        when(movieRepository.save(movie)).thenReturn(new Movie(
                3L,
                "new title",
                "new image",
                List.of(new Character()),
                new Gender("gender name", "gender image")
        ));

        Movie movieSaved = movieRepository.save(movie);

        verify(movieRepository, times(1)).save(movie);
        assertInstanceOf(Movie.class, movieSaved);

        assertEquals(3L, movieSaved.getId());
        assertEquals("new title", movieSaved.getTitle());
        assertEquals("new image", movieSaved.getImage());
        assertEquals(1, movieSaved.getCharacters().size());
        assertEquals("gender name", movieSaved.getGender().getName());
        assertEquals("gender image", movieSaved.getGender().getImage());
    }

    @Test
    void updateMovie() {
        Movie movie = new Movie(
                3L,
                "new title",
                "new image",
                List.of(new Character()),
                new Gender("gender name", "gender image")
        );

        when(movieRepository.save(movie)).thenReturn(movie);

        movie.setCharacters(List.of(new Character()));

        Movie movieupdated = movieRepository.save(movie);

        verify(movieRepository, times(1)).save(movie);
        assertInstanceOf(Movie.class, movieupdated);

        assertEquals(3L, movieupdated.getId());
        assertEquals("new title", movieupdated.getTitle());
        assertEquals("new image", movieupdated.getImage());
        assertEquals(movie.getCharacters(), movieupdated.getCharacters());
        assertEquals("gender name", movieupdated.getGender().getName());
        assertEquals("gender image", movieupdated.getGender().getImage());
    }

    @Test
    void deleteMovie() {
        assertEquals(2, movies.size());
        when(movieRepository.delete(1L)).thenReturn(List.of(movies.remove(0)).size());

        int deletedRows = movieRepository.delete(1L);

        assertEquals(1, deletedRows);
        assertEquals(1, movies.size());
        assertEquals(2L, movies.get(0).getId());

        verify(movieRepository, times(1)).delete(1L);
    }

    @Test
    void addCharacterToMovie() {
        Character character = new Character(1L,null, null, null, null, null, null);

        Movie movie = new Movie("New movie", "New image", LocalDate.now());

        when(characterRepository.getById(1L)).thenReturn(character);
        when(movieRepository.findById(3L)).thenReturn(Optional.of(movies.get(1)));


        Character newCharacter = characterRepository.getById(1L);
        Movie existingMovie = movieRepository.findById(3L).get();
        assertNotNull(existingMovie.getCharacters());
        assertInstanceOf(ArrayList.class, existingMovie.getCharacters());

        assertNotNull(existingMovie.getCharacters());
        assertEquals(1,existingMovie.getCharacters().size());

        List<Character> characters = existingMovie.getCharacters();
        characters.add(newCharacter);
        existingMovie.setCharacters(characters);

        assertEquals(2, existingMovie.getCharacters().size());
        assertEquals(1L, existingMovie.getCharacters().get(1).getId());
        assertInstanceOf(ArrayList.class, existingMovie.getCharacters());
    }
    
}