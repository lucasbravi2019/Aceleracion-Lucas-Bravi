package com.bravi.alkemy.auth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private Boolean accountNonBlocked = true;
    private Boolean accountIsActive = true;
    private Boolean credentialsNonExpired = true;

}
