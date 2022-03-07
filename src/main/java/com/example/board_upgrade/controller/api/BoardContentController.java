package com.example.board_upgrade.controller.api;


import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.dto.BoardContentDTO;
import com.example.board_upgrade.dto.BoardFormDTO;
import com.example.board_upgrade.entity.Board;
import com.example.board_upgrade.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/board")
public class BoardContentController extends AbstractController {

    @Autowired
    BoardService boardService;

    @RequestMapping("/boardContent")
    @ResponseBody
    public JSONObject boardContent(@RequestBody JSONObject object) {

        Long id= object.getLong("id");
        Result<BoardContentDTO> result = boardService.getBoard(id);
        return return2JSON(result);

    }
}