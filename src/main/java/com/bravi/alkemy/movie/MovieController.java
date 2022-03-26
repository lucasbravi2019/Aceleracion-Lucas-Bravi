package com.bravi.alkemy.movie;

import com.bravi.alkemy.movie.dto.MovieDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Record> saveMovie(@Valid @RequestBody MovieDTO dto) {
        return new ResponseEntity<>(movieService.saveMovie(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Record>> fetchAllMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long genderId,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        return new ResponseEntity<>(movieService.fetchAllMovies(title, genderId, order), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Record> fetchMovieById(@PathVariable Long id) {
        return new ResponseEntity<>(movieService.fetchMovieById(id), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Record> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieDTO dto) {
        return new ResponseEntity<>(movieService.updateMovie(id, dto), HttpStatus.OK);
    }

    @PutMapping("/{id}/delete")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        int rowsDeleted = movieService.deleteMovie(id);
        return new ResponseEntity<>("Movie with Id: " + id + ", rows deleted: " + rowsDeleted, HttpStatus.OK);
    }

    @PutMapping("/{movieId}/character/{characterId}")
    public ResponseEntity<Record> addCharacterToMovie(@PathVariable Long movieId, @PathVariable Long characterId) {
        return new ResponseEntity<>(movieService.addCharacterToMovie(movieId, characterId), HttpStatus.OK);
    }

}
