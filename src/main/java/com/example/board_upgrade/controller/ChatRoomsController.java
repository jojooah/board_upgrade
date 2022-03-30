package com.example.board_upgrade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class ChatRoomsController {

    @RequestMapping("/Chat")
    public String ChatRooms() {
        return "Chat/chat";
    }
}
