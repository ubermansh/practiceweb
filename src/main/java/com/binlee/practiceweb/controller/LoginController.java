package com.binlee.practiceweb.controller;

import com.binlee.practiceweb.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    MemberService memberService;


    @RequestMapping(value = "/member/login", method = RequestMethod.GET)
    public String loginForm(){
        logger.info("로그인페이지 진입");
        return "/member/loginForm";
    }

    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public String adminLogin(){
        return "/admin/admin";
    }

    @RequestMapping(value="/member/logout")
    public String logout(){
        return "/member/loginForm";
    }

    @RequestMapping(value="/member/denied")
    public String denied(){
        return "/member/denied";
    }

//    @RequestMapping(value="/member/loginProc", method = RequestMethod.POST)
//        public String loginProc(Member member){
//            logger.info("로그인처리 시작");
//            logger.info("member : " + member);
//            String email = member.getEmail();
//            memberService.loadUserByUsername(email);
//
//        return "redirect:/";
//    }




}
