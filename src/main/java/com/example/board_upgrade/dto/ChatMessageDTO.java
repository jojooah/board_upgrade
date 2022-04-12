package com.example.board_upgrade.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageDTO {
    Long ChatRoomId;
    String message;
    Long memberId;

}
