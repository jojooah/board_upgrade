package com.example.board_upgrade.service;

import com.example.board_upgrade.constant.Category;
import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.constant.ResultCode;
import com.example.board_upgrade.dto.BoardContentDTO;
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
import java.util.Optional;


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
        map.put("total", boardPage.getTotalElements()); //총 데이터 개수
        map.put("totalPages", boardPage.getTotalPages());//총 페이지
        map.put("contents", boardPage.getContent());
        return ResultCode.Success.result(map);
    }

    public Result<BoardContentDTO> getBoard(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (!optionalBoard.isPresent()) {
            return ResultCode.FAIL_TO_GET_BOARD.result();
        } else {
            Board board = optionalBoard.get();

            BoardContentDTO boardContentDTO = new BoardContentDTO();
            boardContentDTO.setContent(board.getContent());
            boardContentDTO.setLike(board.getLike());
            boardContentDTO.setTitle(board.getTitle());

            if (board.getMember() == null) {
                boardContentDTO.setName("익명의 사용자");
            } else {
                boardContentDTO.setName(board.getMember().getName());
            }
            return ResultCode.Success.result(boardContentDTO);
        }

    }

    public Result<Board> deleteBoard(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            Board deleteBoard=optionalBoard.get();
            boardRepository.delete(deleteBoard);
            return ResultCode.Success.result();
        } else {
            return ResultCode.FAIL_TO_DELETE_BOARD.result();
        }
    }

    public Result<Board> updateBoard(BoardFormDTO boardFormDTO){
        return updateBoard(boardFormDTO.getTitle(),boardFormDTO.getContent(),boardFormDTO.getId());
    }

    public Result<Board> updateBoard(String title,String content,Long id){
        Optional<Board> optionalBoard=boardRepository.findById(id);
        if(optionalBoard.isPresent()){
            Board updateBoard=optionalBoard.get();

            updateBoard.setTitle(title);
            updateBoard.setContent(content);
            boardRepository.save(updateBoard);
            return ResultCode.Success.result();

        }else{
            return ResultCode.FAIL_TO_DELETE_BOARD.result();//이미 삭제된 글입니다.
        }
    }
}
