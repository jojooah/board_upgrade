package com.example.board_upgrade.controller.api;

import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.constant.SessionConst;
import com.example.board_upgrade.dto.LoginFormDTO;
import com.example.board_upgrade.entity.Member;
import com.example.board_upgrade.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController extends AbstractController {

    @Autowired
    private MemberService memberService;


    @RequestMapping("/login")
    @ResponseBody
    public JSONObject login(@RequestBody LoginFormDTO loginFormDTO, HttpServletRequest request) {//제이슨으로 받겠다s
        log.info(loginFormDTO.toString());
        Result result = memberService.login(loginFormDTO);

        if (result.isSuccess()) {
            Member member = (Member) result.getResultObject();
            HttpSession session = request.getSession(true);
            session.setAttribute(SessionConst.LOGIN_USER, member);
        }
        return return2JSON(result);
    }
}
