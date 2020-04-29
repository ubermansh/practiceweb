package com.binlee.practiceweb.controller;

import com.binlee.practiceweb.dto.Member;
import com.binlee.practiceweb.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JoinController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MemberService memberService;

    public JoinController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 페이지
    @RequestMapping(value="/member/join", method = RequestMethod.GET)
    public String Join(){
        return "/member/joinForm";
    }

    // 회원가입 처리
    @RequestMapping(value="/member/joinProc", method = RequestMethod.POST)
    public String joinProc(Member member){

        logger.info("member : " + member);
        memberService.joinProc(member);
        return "redirect:/";
    }



}
