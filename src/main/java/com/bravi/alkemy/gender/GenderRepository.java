package com.bravi.alkemy.gender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GenderRepository extends JpaRepository<Gender, Long> {

    @Modifying
    @Query(value = "UPDATE Movie m SET m.gender = null WHERE m.gender.id = ?1")
    int removeGendersFromMovies(Long id);

    @Modifying
    @Query(value = "UPDATE Gender g SET g.deleted = true WHERE g.id = ?1")
    int delete(Long id);

}
