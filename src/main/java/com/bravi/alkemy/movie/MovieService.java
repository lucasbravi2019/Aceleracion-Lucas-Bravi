package com.bravi.alkemy.movie;

import com.bravi.alkemy.character.Character;
import com.bravi.alkemy.character.CharacterRepository;
import com.bravi.alkemy.exception.MovieNotFoundException;
import com.bravi.alkemy.generic.RecordType;
import com.bravi.alkemy.movie.dto.MovieDTO;
import com.bravi.alkemy.movie.dto.MovieFilterDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CharacterRepository characterRepository;
    private final MovieMapper movieMapper;
    private final MovieSpecification movieSpecification;

    public MovieService(
            MovieRepository movieRepository,
            CharacterRepository characterRepository,
            MovieMapper movieMapper,
            MovieSpecification movieSpecification
    ) {
        this.movieRepository = movieRepository;
        this.characterRepository = characterRepository;
        this.movieMapper = movieMapper;
        this.movieSpecification = movieSpecification;
    }

    public List<Record> fetchAllMovies(String title, Long genderId, String order) {
        MovieFilterDTO dto = new MovieFilterDTO(title, genderId, order);
        return movieMapper.toDTOList(
                        movieRepository.findAll(
                                movieSpecification.getByFilters(dto)
                        )
                )
                .stream()
                .map(movie -> movie.generateRecord(RecordType.BASIC))
                .collect(Collectors.toList());
    }

    public Record fetchMovieById(Long id) {
        return movieMapper.toDTO(
                        movieRepository.findById(id)
                                .orElseThrow(
                                        () -> new MovieNotFoundException(id)
                                )
                )
                .generateRecord(RecordType.DETAILED);
    }

    public Record saveMovie(MovieDTO dto) {
        return movieMapper.toDTO(
                        movieRepository.save(
                                movieMapper.toEntity(dto)
                        )
                )
                .generateRecord(RecordType.DETAILED);
    }

    public Record updateMovie(Long id, MovieDTO dto) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(
                        () -> new MovieNotFoundException(id)
                );
        movie.setTitle(dto.getTitle());
        movie.setImage(dto.getImage());
        return movieMapper.toDTO(
                        movieRepository.save(movie)
                )
                .generateRecord(RecordType.DETAILED);
    }

    @Transactional
    public int deleteMovie(Long id) {
        int charactersRemoved = movieRepository.removeCharactersFromMovie(id);
        int deletedRows = movieRepository.delete(id);
        if (deletedRows == 0) throw new MovieNotFoundException(id);
        return deletedRows;
    }

    public Record addCharacterToMovie(Long movieId, Long characterId) {
        Character character = characterRepository.getById(characterId);
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(
                        () -> new MovieNotFoundException(movieId)
                );
        movie.getCharacters().add(character);
        return movieMapper.toDTO(
                        movieRepository.save(movie)
                )
                .generateRecord(RecordType.DETAILED);
    }




}
