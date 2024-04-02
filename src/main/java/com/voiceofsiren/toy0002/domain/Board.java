package com.voiceofsiren.toy0002.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.voiceofsiren.toy0002.dto.BoardDTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String title;

    @NotNull
    private String content;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //== 연관 관계 메서드 ==//
    public void addUser(User user) {
        this.setUser(user);
        user.getBoards().add(this);
    }


    public Board() {

    }

    public Board(BoardDTO boardDTO) {
        this.id = boardDTO.getId();
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board board)) return false;
        return Objects.equals(getId(), board.getId()) && Objects.equals(getTitle(), board.getTitle()) && Objects.equals(getContent(), board.getContent()) && Objects.equals(getUser(), board.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getContent(), getUser());
    }
}
