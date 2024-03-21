package com.voiceofsiren.toy0002.controller;

import com.voiceofsiren.toy0002.dto.BoardDTO;
import com.voiceofsiren.toy0002.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/boards")
    List<BoardDTO> all(@RequestParam(required = false) String title,
                       @RequestParam(required = false) String content,
                       @PageableDefault Pageable pageable) {
        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            List<BoardDTO> boards = boardService.findAll();
            boards.stream().forEach(boardDTO -> boardDTO.getUser().getUsername());
            return boards;
        } else if (!StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return boardService.findByTitle(title);
        } else if (StringUtils.isEmpty(title) && !StringUtils.isEmpty(content)) {
            return boardService.findByContent(content);
        } else {
            return boardService.findByTitleOrContent(title, content);
        }
    }

    @PostMapping("/boards")
    BoardDTO newBoard(@RequestBody BoardDTO boardDTO) {
        return boardService.save(boardDTO);
    }

    @GetMapping("/boards/{id}")
    BoardDTO one(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @PutMapping("/boards/{id}")
    BoardDTO replaceBoard(@RequestBody BoardDTO newBoardDTO, @PathVariable Long id) {
        return boardService.replace(newBoardDTO, id);
    }

    @DeleteMapping("/boards/{id}")
    @Secured("ROLE_ADMIN")  // 권한이 맞지 않을 경우: 403 Forbidden Error 발생
    void deleteBoard(@PathVariable Long id) {
        boardService.deleteById(id);
    }
}