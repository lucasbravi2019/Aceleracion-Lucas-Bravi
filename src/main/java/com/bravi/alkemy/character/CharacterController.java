package com.bravi.alkemy.character;

import com.bravi.alkemy.character.dto.CharacterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("characters")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping
    public ResponseEntity<Record> saveCharacter(@Valid @RequestBody CharacterDTO dto) {
        return new ResponseEntity<>(characterService.saveCharacter(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Record>> fetchAllCharacters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Float weight,
            @RequestParam(required = false) Byte age,
            @RequestParam(required = false) List<Long> moviesId
    ) {
        return new ResponseEntity<>(
                characterService.fetchAllCharacters(name, weight, age, moviesId),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Record> fetchCharacter(@PathVariable Long id) {
        return new ResponseEntity<>(characterService.fetchCharacterById(id), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Record> updateCharacter(@PathVariable Long id, @Valid @RequestBody CharacterDTO dto) {
        return new ResponseEntity<>(characterService.updateCharacter(id, dto), HttpStatus.OK);
    }

    @PutMapping("/{id}/delete")
    public ResponseEntity<String> deleteCharacter(@PathVariable Long id) {
        int deletedRows = characterService.deleteCharacter(id);
        return new ResponseEntity<>("Character with id: " + id + ", rows deleted " + deletedRows, HttpStatus.OK);
    }

}
