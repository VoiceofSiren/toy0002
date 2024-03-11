package com.voiceofsiren.toy0002.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<UserRole> userRoles = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.MERGE,  // UserService에서 boards 값을 할당하기 위한 설정
            orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    //== 연관 관계 메서드 ==//
    public void addUserRole(UserRole userRole) {
        this.userRoles.add(userRole);
        userRole.setUser(this);
    }

    //== 연관 관계 메서드 ==//
    public void addBoard(Board board) {
        board.setUser(this);
        this.boards.add(board);
    }


    public User() {

    }

    public User(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.enabled = userDTO.getEnabled();
    }
}
