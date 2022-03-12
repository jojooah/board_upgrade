package com.example.board_upgrade.repository;

import com.example.board_upgrade.entity.Board;
import com.example.board_upgrade.entity.Member;
import com.example.board_upgrade.entity.MemberLikeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MemberLikeBoardRepository extends JpaRepository<MemberLikeBoard,Long> {

    Optional<MemberLikeBoard> findMemberLikeBoardByMemberAndBoard(Member member, Board board);
}
