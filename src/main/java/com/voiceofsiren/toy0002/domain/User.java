package com.voiceofsiren.toy0002.domain;

import com.voiceofsiren.toy0002.dto.UserDTO;
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

    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    //== 연관 관계 메서드 ==//
    public void addUserRole(UserRole userRole) {
        this.userRoles.add(userRole);
        userRole.setUser(this);
    }

    public User() {

    }

    public User(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.enabled = userDTO.getEnabled();

    }
}
