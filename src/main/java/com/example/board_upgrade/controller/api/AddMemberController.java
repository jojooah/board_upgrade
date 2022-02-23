package com.example.board_upgrade.controller.api;


import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.dto.MemberFormDTO;
import com.example.board_upgrade.service.MemberService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class AddMemberController extends AbstractController{

    @Autowired
    private MemberService memberService;

    @RequestMapping("/addMember")
    public @ResponseBody JSONObject addMember(@RequestBody MemberFormDTO memberFormDTO) {
        Result result = memberService.addMember(memberFormDTO);
        return return2JSON(result);


    }

}
