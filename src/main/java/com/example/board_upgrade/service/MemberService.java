package com.example.board_upgrade.service;

import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.constant.ResultCode;
import com.example.board_upgrade.dto.LoginFormDTO;
import com.example.board_upgrade.entity.Member;
import com.example.board_upgrade.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Result<Member> login(String email, String pwd) {
        try {
            Optional<Member> optionalMember = memberRepository.findByEmail(email);
            if (optionalMember.isPresent()) {
                Member member = optionalMember.get();
                if (member.getPassword().equals(pwd)) {
                    return ResultCode.Success.result(member);
                } else {
                    return ResultCode.PASSWORD_NOT_CORRECT.result();
                }
            } else {
                return ResultCode.UNKNOWN_USER.result();
            }
        } catch (Exception e) {
            return ResultCode.ETC_ERROR.result();
        }
    }

    public Result<Member> login(LoginFormDTO loginFormDTO) {
        return login(loginFormDTO.getEmail(), loginFormDTO.getPassword());
    }

}
