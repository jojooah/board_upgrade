package com.example.board_upgrade.controller.api;


import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.dto.BoardListDTO;
import com.example.board_upgrade.dto.ChatDTO;
import com.example.board_upgrade.dto.ChatMessageDTO;
import com.example.board_upgrade.dto.CreateChatRoomDTO;
import com.example.board_upgrade.entity.ChatMessage;
import com.example.board_upgrade.entity.ChatParticipants;
import com.example.board_upgrade.entity.ChatRoom;
import com.example.board_upgrade.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Slf4j
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
        Result<List<ChatRoom>> result = chatService.getChatRooms();
        return return2JSON(result);
    }

    @GetMapping("/chatRooms/{chatId}") //동사/목적어   복수/단수  이렇게있으면 복수중에서 그 id로 찾아냄.
    @ResponseBody
    public JSONObject getChatRoom(@PathVariable Long chatId) {
        Result<ChatRoom> result = chatService.getChatRoom(chatId);
        return return2JSON(result);
    }

    @PostMapping("/chatRooms/{chatRoomId}/message")
    @ResponseBody
    public JSONObject addChatMessage(@RequestBody ChatMessageDTO chatMessageDTO) {
        Result<ChatMessage> result = chatService.addChatMessage(chatMessageDTO);
        return return2JSON(result);
    }

    @GetMapping("/chatRooms/{chatRoomId}/messages")
    @ResponseBody
    public Map<String, Object> getChatMessages(@PathVariable Long chatRoomId){
        Result<Map<String,Object>> result=chatService.getChatMessages(chatRoomId);
        return return2Map(result);
    }


    @PostMapping("/chatRooms/{chatRoomId}/member")
    @ResponseBody
    public JSONObject enterChatRoom(@PathVariable Long chatRoomId,@RequestBody JSONObject jsonObject) {
        Long memberId = jsonObject.getLong("memberId");
        Result<ChatDTO> result = chatService.enterChatRoom(chatRoomId,memberId);
        return return2JSON(result);
    }



}
