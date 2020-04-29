package com.binlee.practiceweb.service;

import com.binlee.practiceweb.dao.MemberDao;
import com.binlee.practiceweb.dto.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        logger.info("## loadUserByUsername");
        logger.info("이메일 값" + email);

        Member member = memberDao.findById(email);
        logger.info("## loadUserByUsername 끝나고 member : " + member);
        return member;
    }

    @Override
    public void joinProc(Member member) {

        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPwd(passwordEncoder.encode(member.getPassword()));

        logger.info("serviceimpl member : " + member);

        memberDao.insertMember(member);
    }
}
