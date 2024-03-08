package com.voiceofsiren.toy0002.controller;

import com.voiceofsiren.toy0002.dto.UserDTO;
import com.voiceofsiren.toy0002.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @PostMapping("/register")
    public String registerForm(UserDTO userDTO) {
        userService.save(userDTO);
        return "account/login";
    }
}
