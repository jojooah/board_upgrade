package com.example.board_upgrade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class CommuFormPageController {
    @RequestMapping("/community_form")
    public String commuForm(){
        return "board/commuForm";
    }
}
