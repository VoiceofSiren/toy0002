package com.voiceofsiren.toy0002.dto;

import com.voiceofsiren.toy0002.domain.Board;
import lombok.Data;

@Data
public class BoardPageDTO {

    private Long id;
    private String title;
    private String username;

    public BoardPageDTO() {

    }

    public BoardPageDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
    }

    public BoardPageDTO(Long id, String title, String username) {
        this.id = id;
        this.title = title;
        this.username = username;
    }
}
