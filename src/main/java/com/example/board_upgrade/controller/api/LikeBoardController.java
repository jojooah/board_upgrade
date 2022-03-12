package com.example.board_upgrade.controller.api;

import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.service.BoardService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
public class LikeBoardController extends AbstractController {
    @Autowired
    BoardService boardService;


    @RequestMapping("/like")
    @ResponseBody
    public JSONObject likeBoard(@RequestBody JSONObject object) {
        Long boardId = object.getLong("boardId");
        Long memberId = object.getLong("memberId");
        Result result = boardService.getBoardLike(boardId, memberId);
        return return2JSON(result);
    }
}
