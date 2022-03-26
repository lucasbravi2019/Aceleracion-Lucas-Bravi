package com.bravi.alkemy.gender;

import com.bravi.alkemy.gender.dto.GenderDTO;
import com.bravi.alkemy.generic.RecordType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenderServiceTest {

    @Mock
    GenderRepository genderRepository;

    @InjectMocks
    GenderMapper genderMapper;

    @Test
    void saveGender() {
        Gender gender = new Gender("Accion", "Imagen de accion");
        when(genderRepository.save(gender)).thenReturn(new Gender(1L, "Accion", "Imagen de accion"));

        assertNotNull(genderRepository);
        assertNotNull(genderMapper);

        Gender genderSaved = genderRepository.save(gender);

        assertEquals(1l, genderSaved.getId());
        assertEquals("Accion", genderSaved.getName());
        assertEquals("Imagen de accion", genderSaved.getImage());

        assertInstanceOf(Gender.class, genderSaved);
        assertInstanceOf(GenderDTO.class, genderMapper.toDTO(genderSaved));

        assertEquals(1L, genderMapper.toDTO(genderSaved).getId());
        assertEquals("Accion", genderMapper.toDTO(genderSaved).getName());
        assertEquals("Imagen de accion", genderMapper.toDTO(genderSaved).getImage());

        assertInstanceOf(
                Record.class,
                genderMapper.toDTO(genderSaved)
                        .generateRecord(RecordType.BASIC)
        );

        assertInstanceOf(
                Record.class,
                genderMapper.toDTO(genderSaved)
                        .generateRecord(RecordType.DETAILED)
        );

        assertNull(genderMapper.toDTO(genderSaved)
                .generateRecord(RecordType.DETAILED_WITHOUT_RELATIONSHIP)
        );


    }
}