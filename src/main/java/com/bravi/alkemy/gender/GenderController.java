package com.bravi.alkemy.gender;

import com.bravi.alkemy.gender.dto.GenderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("genders")
public class GenderController {

    private final GenderService genderService;

    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @PostMapping
    public ResponseEntity<Record> saveGender(@Valid @RequestBody GenderDTO dto) {
        return new ResponseEntity<>(genderService.saveGender(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/delete")
    public ResponseEntity<String> deleteGender(@PathVariable Long id) {
        int deletedRows = genderService.deleteGender(id);
        return new ResponseEntity<>("Gender with ID: " + id + ", deleted rows: " + deletedRows, HttpStatus.OK);
    }

}
