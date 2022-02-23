package com.example.board_upgrade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginPageController {

    @RequestMapping("/login")
    public String login(@RequestParam(required = false) String message, Model model){

        model.addAttribute("message",message);

        return "member/loginForm";
    }
}
