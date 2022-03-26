package com.bravi.alkemy.generic;

import java.util.List;

/**
 * Interface used in every Mapper to transform Entities into DTO objects, and DTO objects into Entities
 * @param <E> Entity used
 * @param <T> DTO subclass of IGenericDTO used to map the entity
 */
public interface IGenericMapper<E, T extends IGenericDTO> {

    public E toEntity(T dto);
    public T toDTO(E entity);
    public List<T> toDTOList(List<E> list);

}
