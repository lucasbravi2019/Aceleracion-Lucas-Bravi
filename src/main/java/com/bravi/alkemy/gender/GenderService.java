package com.bravi.alkemy.gender;

import com.bravi.alkemy.gender.dto.GenderDTO;
import com.bravi.alkemy.exception.GenderNotFoundException;
import com.bravi.alkemy.generic.RecordType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenderService {

    private final GenderRepository genderRepository;
    private final GenderMapper genderMapper;

    public GenderService(GenderRepository genderRepository, GenderMapper genderMapper) {
        this.genderRepository = genderRepository;
        this.genderMapper = genderMapper;
    }

    public Record saveGender(GenderDTO dto) {
        return genderMapper.toDTO(
                        genderRepository.save(
                                genderMapper.toEntity(dto)
                        )
                )
                .generateRecord(RecordType.DETAILED);
    }

    @Transactional
    public int deleteGender(Long id) {
        int genderDeletedFromMovies = genderRepository.removeGendersFromMovies(id);
        int deletedRows = genderRepository.delete(id);
        if (deletedRows == 0) throw new GenderNotFoundException(id);
        return deletedRows;
    }

}
