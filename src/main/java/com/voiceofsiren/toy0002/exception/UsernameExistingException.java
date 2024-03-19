package com.voiceofsiren.toy0002.exception;

public class UsernameExistingException extends RuntimeException{
    public UsernameExistingException(String username) {
        super(username + "은 이미 존재하는 이름입니다.");
    }
}
