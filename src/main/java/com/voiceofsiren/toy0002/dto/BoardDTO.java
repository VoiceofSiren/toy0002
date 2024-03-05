package com.voiceofsiren.toy0002.dto;

import com.voiceofsiren.toy0002.domain.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BoardDTO {

    private Long id;
    private String title;
    private String content;

    public BoardDTO() {

    }

    public BoardDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
