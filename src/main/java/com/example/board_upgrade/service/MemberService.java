package com.example.board_upgrade.service;

import com.example.board_upgrade.constant.Level;
import com.example.board_upgrade.constant.Result;
import com.example.board_upgrade.constant.ResultCode;
import com.example.board_upgrade.constant.Role;
import com.example.board_upgrade.dto.LoginFormDTO;
import com.example.board_upgrade.dto.MemberFormDTO;
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

    public Result<Member> addMember(String email, String name, String password) {
        Optional<Member> emailMember = memberRepository.findByEmail(email);
        Optional<Member> nameMember = memberRepository.findByName(name);
        try {
            if (emailMember.isPresent()) {
                return ResultCode.USER_ALREADY_EXISTS.result();
            }else if(nameMember.isPresent()){
                return ResultCode.NAME_ALREADY_EXISTS.result();
            }
            else {
                Member member = new Member();
                member.setEmail(email);
                member.setName(name);
                member.setLevel(Level.level1);
                member.setRole(Role.USER);
                member.setPassword(password);
                memberRepository.save(member);
                return ResultCode.Success.result();
            }
        } catch (Exception e) {
            return ResultCode.ETC_ERROR.result();

        }
    }

    public Result<Member> addMember(MemberFormDTO memberFormDTO) {
        return addMember(memberFormDTO.getEmail(), memberFormDTO.getName(), memberFormDTO.getPassword());
    }

}
