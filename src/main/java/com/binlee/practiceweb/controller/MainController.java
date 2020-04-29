package com.binlee.practiceweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String main(){

        // 사용자 세션 정보 SpringContextHolder를 이용
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info(authentication.getName());
        logger.info(authentication.toString());


        return "/main";
    }

    @GetMapping("/member/info")
    public String myInfo(){
        return "/member/myinfo";
    }
}
