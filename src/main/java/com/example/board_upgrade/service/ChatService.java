package com.example.board_upgrade.service;

import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.constant.ResultCode;
import com.example.board_upgrade.dto.ChatDTO;
import com.example.board_upgrade.dto.ChatMessageDTO;
import com.example.board_upgrade.dto.CreateChatRoomDTO;
import com.example.board_upgrade.entity.ChatMessage;
import com.example.board_upgrade.entity.ChatParticipants;
import com.example.board_upgrade.entity.ChatRoom;
import com.example.board_upgrade.entity.Member;
import com.example.board_upgrade.repository.ChatMessageRepository;
import com.example.board_upgrade.repository.ChatParticipantsRepository;
import com.example.board_upgrade.repository.ChatRoomRepository;
import com.example.board_upgrade.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class ChatService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private ChatParticipantsRepository chatParticipantsRepository;

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

    public Result<Map<String,Object>> getChatMessages(Long chatRoomId) {
        try {
            Map<String,Object> map=new HashMap<>();
            List<ChatMessage> chatMessageList = chatMessageRepository.findChatMessageByChatRoomId(chatRoomId);
            List<ChatMessageDTO> chatMessageDTOList=new ArrayList<>();

            for(ChatMessage chatMessage:chatMessageList){

                ChatMessageDTO chatMessageDTO=new ChatMessageDTO();
                chatMessageDTO.setMessage(chatMessage.getMessage());
                chatMessageDTOList.add(chatMessageDTO);
            }

            map.put("messages",chatMessageDTOList);
            return ResultCode.Success.result(map);
        } catch (Exception e) {
            return ResultCode.DB_ERROR.result();
        }
    }


    public Result<ChatMessage> addChatMessage(Long roomId, String message, Long memberId) {
        try {
            Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(roomId);
            Optional<Member> optionalMember = memberRepository.findById(memberId);
            if (optionalChatRoom.isPresent()) {
                ChatMessage chatMessage = ChatMessage.builder()
                        .message(message)
                        .chatRoom(optionalChatRoom.get())
                        .member(optionalMember.get())
                        .updateTime(LocalDateTime.now())
                        .build();
                chatMessageRepository.save(chatMessage);
                return ResultCode.Success.result(chatMessage);
            } else {
                //채팅방이 없습니다.
                return ResultCode.CHATROOM_NOT_EXIST.result();
            }

        } catch (Exception e) {
            return ResultCode.ETC_ERROR.result();
        }
    }

    public Result<ChatMessage> addChatMessage(ChatMessageDTO chatMessageDTO) {
        return addChatMessage(chatMessageDTO.getChatRoomId(), chatMessageDTO.getMessage(), chatMessageDTO.getMemberId());
    }

    public Result<ChatDTO> enterChatRoom(Long chatRoomId, Long memberId) {
        try {

            Optional<ChatParticipants> optionalChatParticipants = chatParticipantsRepository.findChatParticipantsByMemberIdAndChatRoomId(memberId, chatRoomId);
            if (optionalChatParticipants.isPresent()) {
                // log.info("이미 입장한 채팅방입니다");
                Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(chatRoomId);
                ChatRoom chatRoom = optionalChatRoom.get();
                ChatDTO chatDTO=new ChatDTO();
                chatDTO.setChatRoomId(chatRoom.getId());
                return ResultCode.Success.result(chatDTO);
            }

            Optional<Member> optionalMember = memberRepository.findById(memberId);
            Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(chatRoomId);

            if (optionalChatRoom.isPresent() && optionalMember.isPresent()) {
                ChatRoom chatRoom = optionalChatRoom.get();
                Member member = optionalMember.get();
                ChatParticipants chatParticipants = ChatParticipants.builder()
                        .chatRoom(chatRoom)
                        .member(member)
                        .build();

                chatParticipantsRepository.save(chatParticipants);

                ChatDTO chatDTO=new ChatDTO();
                chatDTO.setChatRoomId(chatRoom.getId());
                return ResultCode.Success.result(chatDTO);

            } else {
                return ResultCode.CHATROOM_NOT_EXIST.result();
            }
        } catch (Exception e) {
            return ResultCode.ETC_ERROR.result();
        }
    }
}
