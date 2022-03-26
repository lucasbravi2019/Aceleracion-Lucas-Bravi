package com.bravi.alkemy.gender;

import com.bravi.alkemy.movie.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "genders")
@Where(clause = "deleted = false")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String image;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "gender")
    private List<Movie> movies;

    public Gender(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Gender(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

}
