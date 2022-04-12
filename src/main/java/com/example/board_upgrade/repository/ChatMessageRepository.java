package com.example.board_upgrade.repository;

import com.example.board_upgrade.entity.ChatMessage;
import com.example.board_upgrade.entity.ChatRoom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    @EntityGraph(attributePaths = {"member","chatRoom"})
    List<ChatMessage> findChatMessageByChatRoomId(Long id);
}
