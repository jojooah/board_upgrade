package com.example.board_upgrade.repository;

import com.example.board_upgrade.constant.Category;
import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.entity.Board;
import com.example.board_upgrade.entity.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {


}