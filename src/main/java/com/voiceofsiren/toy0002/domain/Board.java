package com.voiceofsiren.toy0002.domain;

import com.voiceofsiren.toy0002.dto.BoardDTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Board() {

    }

    public Board(BoardDTO boardDTO) {
        this.id = boardDTO.getId();
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
        this.user = boardDTO.getUser();
    }

}
