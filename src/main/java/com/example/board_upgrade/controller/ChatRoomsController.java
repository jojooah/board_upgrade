package com.example.board_upgrade.controller;

import com.example.board_upgrade.constant.SessionConst;
import com.example.board_upgrade.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller

public class ChatRoomsController {

    @RequestMapping("/Chat/{chatRoomId}")
    public String ChatRooms(Model model,
                            @PathVariable Long chatRoomId,
                            @SessionAttribute(name= SessionConst.LOGIN_USER,required = false) Member loginMember) {
        model.addAttribute("memberId",loginMember.getId());
        model.addAttribute("chatRoomId",chatRoomId);
        return "Chat/chat";
    }
}
