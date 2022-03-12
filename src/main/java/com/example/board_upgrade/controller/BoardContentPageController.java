package com.example.board_upgrade.controller;

import com.example.board_upgrade.constant.SessionConst;
import com.example.board_upgrade.entity.Board;
import com.example.board_upgrade.entity.Member;
import com.example.board_upgrade.entity.MemberLikeBoard;
import com.example.board_upgrade.repository.BoardRepository;
import com.example.board_upgrade.repository.MemberLikeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardContentPageController {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberLikeBoardRepository memberLikeBoardRepository;

    @GetMapping("/content")
    public String BoardContent(@RequestParam Long id, @RequestParam String category, Model model,
                               @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Member loginMember) {

        model.addAttribute("id", id);
        model.addAttribute("category", category);
        Optional<Board> optionalBoard = boardRepository.findById(id);

        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            model.addAttribute("board", board);

            if(board.getMember()!=null) {
                model.addAttribute("memberId", board.getMember().getId());
            }

            if (loginMember != null) {
                Optional<MemberLikeBoard> optionalMemberLikeBoard =
                        memberLikeBoardRepository.findMemberLikeBoardByMemberAndBoard(loginMember, board);
                if (optionalMemberLikeBoard.isPresent()) {
                    model.addAttribute("liked", 2); //이미 좋아요 눌렀어요
                } else {
                    model.addAttribute("liked", 1);//좋아요가능
                }
            } else {
                model.addAttribute("liked", 3);//로그인해주세요
            }

        } else {
            return "redirect:/home";
        }


        return "board/boardContent";
    }

}
