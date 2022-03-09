package com.example.board_upgrade.controller.api;

import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.dto.BoardFormDTO;
import com.example.board_upgrade.entity.Board;
import com.example.board_upgrade.service.BoardService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
public class UpdateBoardController extends AbstractController {

    @Autowired
    BoardService boardService;

    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestBody BoardFormDTO boardFormDTO) {
        Result<Board>result = boardService.updateBoard(boardFormDTO);
        return return2JSON(result);
    }
}
