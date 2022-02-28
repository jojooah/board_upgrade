package com.example.board_upgrade.service;

import com.example.board_upgrade.constant.Category;
import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.constant.ResultCode;
import com.example.board_upgrade.dto.BoardFormDTO;
import com.example.board_upgrade.dto.BoardListDTO;
import com.example.board_upgrade.entity.Board;
import com.example.board_upgrade.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    public Result<Board> addBoard(String title, String content, Category category) {
        log.info("boardservice");
        try {
            Board board = Board.builder()
                    .content(content)
                    .title(title)
                    .updateTime(LocalDateTime.now())
                    .category(category)
                    .build();
            boardRepository.save(board);
            return ResultCode.Success.result();
        } catch (Exception e) {
            return ResultCode.FAIL_TO_REGISTER_BOARD.result();
        }
    }

    public Result<Board> addBoard(BoardFormDTO boardFormDTO) {
        return addBoard(boardFormDTO.getTitle(), boardFormDTO.getContent(), boardFormDTO.getCategory());
    }

    public Result<Map<String, Object>> getBoardPageObject(BoardListDTO boardListDTO) {
        Pageable pageable = PageRequest.of(boardListDTO.getPageNum(), boardListDTO.getCount());
        Page<Board> boardPage = boardRepository.findBoardByCategoryOrderByIdDesc(boardListDTO.getCategory(), pageable);
        
        Map<String, Object> map = new HashMap<>();
        map.put("totalPages", boardPage.getTotalPages());
        List<Board> boardList = boardPage.getContent();
        map.put("contents", boardList);
        return ResultCode.Success.result(map);
    }
}
