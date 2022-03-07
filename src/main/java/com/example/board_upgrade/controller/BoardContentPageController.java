package com.example.board_upgrade.controller;

import com.example.board_upgrade.entity.Board;
import com.example.board_upgrade.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardContentPageController {

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/content")
    public String BoardContent(@RequestParam Long id, @RequestParam String category, Model model) {

        model.addAttribute("id", id);
        model.addAttribute("category", category);
        Optional<Board> optionalBoard=boardRepository.findById(id);
        if(optionalBoard.isPresent()){
            Board board=optionalBoard.get();
            model.addAttribute("board",board);
        }
        return "board/boardContent";
    }
}
