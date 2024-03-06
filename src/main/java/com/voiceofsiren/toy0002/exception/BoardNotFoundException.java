package com.voiceofsiren.toy0002.exception;

public class BoardNotFoundException extends RuntimeException{

    public BoardNotFoundException(Long id) {
        super(id + "번 게시글을 찾을 수 없습니다.");
    }
}
