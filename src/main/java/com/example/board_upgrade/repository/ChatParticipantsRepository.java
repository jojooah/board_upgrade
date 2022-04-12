package com.example.board_upgrade.repository;

import com.example.board_upgrade.entity.ChatParticipants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatParticipantsRepository extends JpaRepository<ChatParticipants,Long> {

    Optional<ChatParticipants> findChatParticipantsByMemberIdAndChatRoomId(Long memberId,Long chatRoomId);
}
