package com.voiceofsiren.toy0002.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.voiceofsiren.toy0002.domain.Board;
import com.voiceofsiren.toy0002.domain.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BoardDTO {

    private Long id;

    @NotNull(message = "제목 입력은 필수입니다.")
    @Size(min = 2, max = 30, message = "제목은 최소 2자, 최대 30자 입력 가능합니다.")
    private String title;

    @NotNull(message = "최소 1글자를 입력해주세요.")
    @Size(min = 1, message = "최소 1글자를 입력해주세요.")
    private String content;

    private UserDTO user;

    public BoardDTO() {

    }

    public BoardDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.user = new UserDTO(board.getUser());
    }

}
