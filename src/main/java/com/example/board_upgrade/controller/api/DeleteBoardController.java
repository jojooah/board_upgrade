package com.example.board_upgrade.controller.api;

import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.entity.Board;
import com.example.board_upgrade.service.BoardService;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
public class DeleteBoardController extends AbstractController {

    @Autowired
    BoardService boardService;

    @GetMapping("/deleteBoard")
    @ResponseBody
    public JSONObject deleteBoard(@RequestParam Long id){
        Result<Board> result=boardService.deleteBoard(id);
        return return2JSON(result);
    }

}
