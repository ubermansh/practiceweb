package com.binlee.practiceweb.service;


import com.binlee.practiceweb.dto.Member;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    /*
     * 일반 회원가입 처리
     * @param member
     */
    public void joinProc(Member member);
}
