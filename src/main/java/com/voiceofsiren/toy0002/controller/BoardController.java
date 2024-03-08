package com.voiceofsiren.toy0002.controller;

import com.voiceofsiren.toy0002.dto.BoardDTO;
import com.voiceofsiren.toy0002.service.BoardService;
import com.voiceofsiren.toy0002.validator.BoardValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model,
                       @PageableDefault(size = 3) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
        //Page<BoardDTO> boards = boardService.findAll(pageable);
        Page<BoardDTO> boards = boardService.findByTitleOrContent(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model,
                       @RequestParam(name = "id", required = false) Long id) {
        if (id == null) {
            model.addAttribute("boardDTO", new BoardDTO());
        } else {
            BoardDTO boardDTO = boardService.findById(id);
            model.addAttribute("boardDTO", boardDTO);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String write(@ModelAttribute @Valid BoardDTO boardDTO,
                        BindingResult bindingResult,
                        Model model) {
        boardValidator.validate(boardDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/form";
        } else {
            boardService.save(boardDTO);
        }
        return "redirect:/board/list";
    }

}
