package com.example.board_upgrade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginPageController {

    @RequestMapping("/login")
    public String login(){
        return "member/loginForm";
    }
}
