package com.example.board_upgrade.controller;

import com.example.board_upgrade.dto.BoardFormDTO;
import com.example.board_upgrade.entity.Board;
import com.example.board_upgrade.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/board")
public class UpdateBoardFormPageController {
    @Autowired
    BoardRepository boardRepository;

    @RequestMapping("/update")
    public String update(@RequestParam Map<String, Object> param,
                         Model model, RedirectAttributes redirectAttributes) {


        Long id = Long.valueOf(String.valueOf(param.get("id")));
        String category="COMMU";
        if(!category.equals(param.get("category"))){
            category="BOOK";
        }

        Optional<Board> optionalBoard = boardRepository.findById(id);

        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            model.addAttribute("id",id);
            model.addAttribute("title", board.getTitle());
            model.addAttribute("content", board.getContent());
            model.addAttribute("category", category);
            log.info("1"+board.getTitle());
            log.info("2"+board.getContent());
            log.info("3"+category);
            log.info("4"+id);
            return "board/updateBoardForm";

        } else {
            redirectAttributes.addAttribute("param", param);
            return "redirect:/boardList";
        }


    }
}
