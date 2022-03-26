package com.bravi.alkemy.character;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long>, JpaSpecificationExecutor<Character> {

    List<Character> findAll(Specification<Character> spec);

    @Modifying
    @Query(value = "DELETE FROM movie_character WHERE character_id = ?1", nativeQuery = true)
    int removeMovieFromCharacter(Long id);

    @Modifying
    @Query(value = "UPDATE Character c SET c.deleted = true WHERE c.id = ?1")
    int delete(Long id);

}
