package com.example.board_upgrade.controller.api;

import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.dto.BoardFormDTO;
import com.example.board_upgrade.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
public class AddBoardController extends AbstractController{

    @Autowired
    private BoardService boardService;

    @RequestMapping("/addBoard")
    @ResponseBody
    public JSONObject addBoard(@RequestBody BoardFormDTO boardFormDTO){
        Result result = boardService.addBoard(boardFormDTO);
        return return2JSON(result);
    }
}
