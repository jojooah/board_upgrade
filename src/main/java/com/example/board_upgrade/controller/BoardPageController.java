package com.example.board_upgrade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class BoardPageController {

    @RequestMapping("/boardList")
    public String boardList(@RequestParam Map<String, Object> param, Model model){
        String category = "COMMU";
        if("BOOK".equals(param.get("category"))){
            category = "BOOK";
        }
        model.addAttribute("category", category);
        return "board/boardList";
    }
}
