package com.voiceofsiren.toy0002.controller;

import com.voiceofsiren.toy0002.dto.UserDTO;
import com.voiceofsiren.toy0002.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "account/register";
    }

    @PostMapping("/register")
    public String register(UserDTO userDTO) {
        userService.save(userDTO);
        return "redirect:/";
    }
}
