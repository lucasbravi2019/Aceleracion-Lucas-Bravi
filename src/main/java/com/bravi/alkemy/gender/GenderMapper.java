package com.bravi.alkemy.gender;

import com.bravi.alkemy.gender.dto.GenderDTO;
import com.bravi.alkemy.gender.dto.GenderDTOBuilder;
import com.bravi.alkemy.generic.IGenericMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenderMapper implements IGenericMapper<Gender, GenderDTO> {

    @Override
    public Gender toEntity(GenderDTO dto) {
        return new GenderBuilder()
                .setName(dto.getName())
                .setImage(dto.getImage())
                .build();
    }

    @Override
    public GenderDTO toDTO(Gender entity) {
        return new GenderDTOBuilder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setImage(entity.getImage())
                .build();
    }

    @Override
    public List<GenderDTO> toDTOList(List<Gender> list) {
        return list.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
