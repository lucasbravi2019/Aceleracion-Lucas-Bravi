package com.bravi.alkemy.movie;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    @Query(value = "SELECT m FROM Movie m LEFT JOIN FETCH m.characters c LEFT JOIN FETCH m.gender g WHERE m.id = ?1")
    Optional<Movie> findById(Long id);


    List<Movie> findAll(Specification<Movie> spec);

    @Modifying
    @Query(value = "DELETE FROM movie_character WHERE movie_id = ?1", nativeQuery = true)
    int removeCharactersFromMovie(Long id);

    @Modifying
    @Query(value = "UPDATE Movie m SET m.deleted = true WHERE m.id = ?1")
    int delete(Long id);

}
