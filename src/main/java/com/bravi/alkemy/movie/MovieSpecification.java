package com.bravi.alkemy.movie;

import com.bravi.alkemy.gender.Gender;
import com.bravi.alkemy.movie.dto.MovieFilterDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<Movie> getByFilters(MovieFilterDTO dto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.hasLength(dto.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + dto.getTitle() + "%"
                        )

                );
            }


            if(dto.getGenderId() != null) {
                Join<Movie, Gender> join = root.join("gender", JoinType.INNER);
                Expression<String> genderId = join.get("id");
                predicates.add(
                        genderId.in(
                                dto.getGenderId()
                        )
                );
            }

            query.distinct(true);

            query.orderBy(
                    dto.isASC() ?
                            criteriaBuilder.asc(
                                    root.<LocalDate>get("createdAt")
                            ) :
                            criteriaBuilder.desc(
                                    root.<LocalDate>get("createdAt")
                            )
            );

            return criteriaBuilder.and(
                    predicates.toArray(
                            new Predicate[0]
                    )
            );
        };
    }

}
