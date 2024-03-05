package com.voiceofsiren.toy0002.domain;

import com.voiceofsiren.toy0002.dto.BoardDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String title;

    @NotNull
    private String content;

    public Board() {

    }

    public Board(BoardDTO boardDTO) {
        this.id = boardDTO.getId();
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
    }
}
