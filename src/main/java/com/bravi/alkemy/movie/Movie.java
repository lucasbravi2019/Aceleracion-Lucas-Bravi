package com.bravi.alkemy.movie;

import com.bravi.alkemy.character.Character;
import com.bravi.alkemy.gender.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "movies")
@Where(clause = "deleted = false")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    private String image;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    private Byte score;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "movie_character",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private List<Character> characters;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Gender gender;

    public Movie(String title, String image, LocalDate createdAt) {
        this.title = title;
        this.image = image;
        this.createdAt = createdAt;
    }

    public Movie(Long id, String title, String image, List<Character> characters, Gender gender) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.characters = characters;
        this.gender = gender;
    }

    public void addCharacter(Character entity) {
        this.characters.add(entity);
    }

    public void deleteCharacter(Character entity) {
        this.characters.remove(entity);
    }

}
