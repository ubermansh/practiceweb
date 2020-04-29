package com.binlee.practiceweb;

import com.binlee.practiceweb.dao.MemberDao;
import com.binlee.practiceweb.dto.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
public class DaoTest {

    @Autowired
    private MemberDao memberDao;

    @Test
    public void getUserList() throws Exception{

        for( Member member : memberDao.selectAll()){
            System.out.println(member.getEmail());
        }

    }


}
