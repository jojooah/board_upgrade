package com.example.board_upgrade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class CommuFormPageController {
    @RequestMapping("/form")
    public String commuForm(@RequestParam String category, Model model){
        model.addAttribute("category",category);
        return "board/boardForm";
    }
}
