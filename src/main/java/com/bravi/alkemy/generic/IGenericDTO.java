package com.bravi.alkemy.generic;

/**
 * DTO interface used in every DTO as a Factory for data representation of different types of Records
 */
public interface IGenericDTO {

    public Record generateRecord(RecordType recordType);

}
