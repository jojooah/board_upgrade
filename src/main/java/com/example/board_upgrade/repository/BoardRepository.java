package com.example.board_upgrade.repository;

import com.example.board_upgrade.constant.Category;
import com.example.board_upgrade.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @EntityGraph(attributePaths = "member")
    Page<Board> findBoardByCategoryOrderByIdDesc(Category category, Pageable pageable);
}