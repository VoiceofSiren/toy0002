package com.voiceofsiren.toy0002.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String username;

    private String password;

    private int enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<>();

}