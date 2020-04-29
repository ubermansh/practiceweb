package com.binlee.practiceweb.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sun.security.util.Password;

import java.util.ArrayList;
import java.util.Collection;

public class Member implements UserDetails {

    private int member_No;
    private String email;
    private String pwd;
    private String nickname;
    private String phone;
    private char verifiy; // 메일 인증
    private int type; // 회원유형(일반, 소셜)
    private boolean enabled;
    private String authority;

    @Override
    public String toString() {
        return "Member{" +
                "member_No=" + member_No +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", verifiy=" + verifiy +
                ", type=" + type +
                ", enabled=" + enabled +
                ", authority='" + authority + '\'' +
                '}';
    }

    public int getMember_No() {
        return member_No;
    }

    public void setMember_No(int member_No) {
        this.member_No = member_No;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public char getVerifiy() {
        return verifiy;
    }

    public void setVerifiy(char verifiy) {
        this.verifiy = verifiy;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(authority));
        return auth;
    }

    @Override
    public String getPassword() {
        return this.pwd;
    } // 사용자 인증 암호

    @Override
    public String getUsername() {
        return this.email;
    } // 사용자 인증 계정

    @Override
    public boolean isAccountNonExpired() {
        return true;
    } // 사용자 계정 만료 여부

    @Override
    public boolean isAccountNonLocked() {
        return true;
    } // 사용자 계정 잠겨 있는지

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    } // 사용자 계정 자격 증명(암호) 만료 여부

    @Override
    public boolean isEnabled() {
        return this.enabled;
    } // 사용자 계정 사용 가능 여부
}
