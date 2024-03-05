package com.voiceofsiren.toy0002.validator;

import com.voiceofsiren.toy0002.domain.Board;
import com.voiceofsiren.toy0002.dto.BoardDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        BoardDTO b = (BoardDTO) obj;

        if(StringUtils.isEmpty(b.getTitle())) {
            e.rejectValue("title", "key1", "제목을 입력하세요");
        } else if (StringUtils.length(b.getTitle()) < 2 || StringUtils.length(b.getTitle()) > 30){
            e.rejectValue("title", "key2", "제목을 2자 이상 30자 이하입니다.");
        }

        if(StringUtils.isEmpty(b.getContent())) {
            e.rejectValue("content", "key3", "내용을 입력하세요.");
        }
    }
}
