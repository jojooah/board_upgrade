package com.example.board_upgrade.service;

import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.constant.ResultCode;
import com.example.board_upgrade.dto.CreateChatRoomDTO;
import com.example.board_upgrade.entity.ChatRoom;
import com.example.board_upgrade.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public Result<ChatRoom> createChatRoom(CreateChatRoomDTO createChatRoomDTO) {
        ChatRoom chatRoom = ChatRoom.builder()
                .title(createChatRoomDTO.getChatRoomTitle())
                .build();
        try {
            return ResultCode.Success.result(chatRoomRepository.save(chatRoom));
        } catch (Exception e) {
            return ResultCode.DB_ERROR.result();
        }


    }

    public Result<List<ChatRoom>> getChatRooms() {
        try {
            List<ChatRoom> chatRooms = chatRoomRepository.findAll();
            return ResultCode.Success.result(chatRooms);
        } catch (Exception e) {
            return ResultCode.DB_ERROR.result(); //만약 디비 에러 났을시. 커넥션 끊기는 등
        }
    }

    public Result<ChatRoom> getChatRoom(Long id) {

        try {
            Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findById(id);
            if (chatRoomOptional.isPresent()) {
                ChatRoom chatRoom = chatRoomOptional.get();
                return ResultCode.Success.result(chatRoom);
            } else {
                return ResultCode.CHATROOM_NOT_EXIST.result();
            }
        } catch (Exception e) { //SQLException e ,null 어쩌구 exception......
            return ResultCode.ETC_ERROR.result();
        }
    }

}
