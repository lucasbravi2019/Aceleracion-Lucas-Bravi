package com.bravi.alkemy.generic;

import java.util.List;

public interface IGenericMapper<E, T extends IGenericDTO> {

    public E toEntity(T dto);
    public T toDTO(E entity);
    public List<T> toDTOList(List<E> list);

}
