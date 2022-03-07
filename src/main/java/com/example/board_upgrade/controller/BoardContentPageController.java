package com.example.board_upgrade.controller;

import com.example.board_upgrade.entity.Board;
import com.example.board_upgrade.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardContentPageController {

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/content")
    public String BoardContent(@RequestParam Long id, @RequestParam String category, Model model) {

        model.addAttribute("id", id);
        model.addAttribute("category", category);
        return "board/boardContent";
    }
}
