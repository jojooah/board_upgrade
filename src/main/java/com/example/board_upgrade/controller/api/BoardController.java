package com.example.board_upgrade.controller.api;

import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.dto.BoardListDTO;
import com.example.board_upgrade.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/board")
public class BoardController extends AbstractController{

    @Autowired
    private BoardService boardService;

    @GetMapping("/getList")
    @ResponseBody
    public Map<String, Object> getList(@RequestBody BoardListDTO boardListDTO){
        Result<Map<String, Object>> result = boardService.getBoardPageObject(boardListDTO);
        return return2Map(result);

    }
}
