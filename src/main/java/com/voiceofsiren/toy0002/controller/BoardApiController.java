package com.voiceofsiren.toy0002.controller;

import com.voiceofsiren.toy0002.dto.BoardDTO;
import com.voiceofsiren.toy0002.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
class BoardApiController {

    private final BoardService boardService;


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/boards")
    List<BoardDTO> all(@RequestParam(required = false) String title,
                       @RequestParam(required = false) String content) {
        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return boardService.findAll();
        } else if (!StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return boardService.findByTitle(title);
        } else if (StringUtils.isEmpty(title) && !StringUtils.isEmpty(content)) {
            return boardService.findByContent(content);
        } else {
            return boardService.findByTitleOrContent(title, content);
        }
    }
    // end::get-aggregate-root[]

    @PostMapping("/boards")
    BoardDTO newEmployee(@RequestBody BoardDTO newEmployee) {
        return boardService.save(newEmployee);
    }

    @GetMapping("/boards/{id}")
    BoardDTO one(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @PutMapping("/boards/{id}")
    BoardDTO replaceEmployee(@RequestBody BoardDTO newBoardDTO, @PathVariable Long id) {
        return boardService.replace(newBoardDTO, id);
    }

    @DeleteMapping("/boards/{id}")
    void deleteEmployee(@PathVariable Long id) {
        boardService.deleteById(id);
    }
}