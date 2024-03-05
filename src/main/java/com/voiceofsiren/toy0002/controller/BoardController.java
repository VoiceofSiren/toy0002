package com.voiceofsiren.toy0002.controller;

import com.voiceofsiren.toy0002.dto.BoardDTO;
import com.voiceofsiren.toy0002.service.BoardService;
import com.voiceofsiren.toy0002.validator.BoardValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private final BoardService boardService;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model) {
        List<BoardDTO> boards = boardService.findAll();
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
        //boardValidator.validate(boardDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("boardDTO", new BoardDTO());
            return "board/form";
        } else {
            boardService.save(boardDTO);
        }
        return "redirect:/board/list";
    }

}
