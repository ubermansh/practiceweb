package com.binlee.practiceweb.dao;

import com.binlee.practiceweb.dto.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDao {

    /**
     * Test용
     * @return
     */
    public List<Member> selectAll();

    /*
    입력 받은 id값으로 계정정보 조회
     */
    public Member findById(String email);

    /*
    회원가입
     */
    public void insertMember(Member member);
}
