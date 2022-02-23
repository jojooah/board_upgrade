package com.example.board_upgrade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddMemberPageController {
    @RequestMapping("/addMember")
    public String addMember(){return "member/addMemberForm";}
}
