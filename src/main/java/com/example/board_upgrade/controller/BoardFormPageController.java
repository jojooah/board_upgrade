package com.example.board_upgrade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardFormPageController {
    @RequestMapping("/form")
    public String form(@RequestParam String category, Model model){
        model.addAttribute("category",category);
        return "board/boardForm";
    }
}
