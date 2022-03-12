package com.example.board_upgrade.controller;

import com.example.board_upgrade.constant.SessionConst;
import com.example.board_upgrade.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardFormPageController {
    @RequestMapping("/form")
    public String form(@RequestParam String category, Model model,
                       @SessionAttribute(name= SessionConst.LOGIN_USER,required = false) Member loginMember ){
        model.addAttribute("category",category);
        if(loginMember!=null){
            model.addAttribute("memberId",loginMember.getId());
            log.info("loginMember="+loginMember.getId().toString());
        }

        return "board/boardForm";
    }
}
