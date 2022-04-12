package com.example.board_upgrade.controller;

import com.example.board_upgrade.constant.SessionConst;
import com.example.board_upgrade.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Map;

@Controller
public class BoardPageController {

    @RequestMapping("/boardList")
    public String boardList(@RequestParam Map<String, Object> param,
                            @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Member loginMember,
                            Model model) {
        String category = "COMMU";
        if ("BOOK".equals(param.get("category"))) {
            category = "BOOK";
        }
        model.addAttribute("category", category);
        if (loginMember != null) {
            model.addAttribute("memberId", loginMember.getId());
        }
        return "board/boardList";
    }
}
