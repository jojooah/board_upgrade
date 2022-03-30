package com.example.board_upgrade.controller.api;


import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.dto.CreateChatRoomDTO;
import com.example.board_upgrade.entity.ChatRoom;
import com.example.board_upgrade.service.ChatService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatRoomController extends AbstractController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/chatRoom")
    @ResponseBody
    public JSONObject createChatRoom(@RequestBody CreateChatRoomDTO createChatRoomDTO) {
        Result<ChatRoom> result = chatService.createChatRoom(createChatRoomDTO);
        return return2JSON(result);
    }

    @GetMapping("/chatRooms")
    @ResponseBody
    public JSONObject getChatRooms() {
        Result<List<ChatRoom>> result = chatService.getChatRooms() ;
        return return2JSON(result);
    }

    @GetMapping("/chatRooms/{chatId}") //동사/목적어   복수/단수  이렇게있으면 복수중에서 그 id로 찾아냄.
    @ResponseBody
    public JSONObject getChatRoom(@PathVariable Long chatId) {
        Result<ChatRoom> result = chatService.getChatRoom(chatId);
        return return2JSON(result);
    }


}
