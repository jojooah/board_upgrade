package com.example.board_upgrade.controller;

import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.entity.ChatRoom;
import com.example.board_upgrade.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ChatRoomPageController {


    @Autowired
    private ChatService chatService;
    public String ChatRoomList(Model model){
        Result<List<ChatRoom>> result=chatService.getChatRooms();

        List<String> chatRoomNameList = new ArrayList<>();
        if(result.isSuccess()) {
            chatRoomNameList = result.getResultObject().stream().map(chatRoom -> chatRoom.getTitle()).collect(Collectors.toList());

        }
        model.addAttribute("chatRoomNameList",chatRoomNameList);
        return "chat/chatRoomList";

    }//사람, 웹툰 리스트 1: n 회차  => 사람과 회차의 조인 테이블로 하는것인가? 혹은 Nosql or 몽고DB 로 하는것인가
    //스프링 4,5차이 자바1.7과 1.8의 차이// 스프링과 스프링부트의 차이//AOP뭘까요//AOP요소//restfulAPI가 어떤 조건을 만족해야 restAPI일까요
    //get과post차이//디비에서 동시에 들어오면 어떻게되냐? 트랜잭션ACID 공부공부
    //CS
}
