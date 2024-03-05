package com.voiceofsiren.toy0002.domain;

import com.voiceofsiren.toy0002.dto.BoardDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    public Board() {

    }

    public Board(BoardDTO boardDTO) {
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getTitle();
    }
}
