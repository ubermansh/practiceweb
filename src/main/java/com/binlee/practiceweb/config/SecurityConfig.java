package com.binlee.practiceweb.config;

import com.binlee.practiceweb.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*
    WebSecurityConfigurerAdapter를 상속받은 클래스에 @EnableWebSecurity 어노테이션을 붙여
    SpringSecurity 설정 클래스라고 명시 해준다.
    @EnableGlobalMethodSecurity을 이용해 Controller 특정 페이지에 특정 권한이 있는 접근만
    허용할 경우 @PreAuthorize 어노테이션을 사용해 활성화 시킬 수 있다.(필수 X)
     */

    // MemberService에서 UserDetailsService를 Extends하고 MemberServiceImpl로 구현한다.
    private final MemberService memberService;
    public SecurityConfig(MemberService memberService){
        this.memberService = memberService;
    }

    /*
    WebSecurity는 FilterChainProxy를 생성하는 필터
    static 디렉터리의 하위 파일(css,img,js 등) 목록 인증 무시 ( = 항상 통과하도록)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/img/**","lib/**");
    }

    /*
    configure(HttpSecurity http) 메소드를 통해 인증 메커니즘과
    HTTP 요청에 대한 웹 기반 보안 구성
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*
        HttpSevletRequest에 따라 접근 제한, 페이지 관련 권한 설정
        .andMatechers메소드 = 특정 경로 지정,
        .permitAll()과 .hasRole() 메소드는 역할(Role)에 따른 접근 설정
         */
        http.authorizeRequests()

                // /admin으로 시작하는 모든 경로는 "ROLE_ADMIN"롤을 가진 사용자만 접근 가능
                .antMatchers("/admin/**").hasRole("ADMIN")

                // /member로 시작하는 모든 경로는 "ROLE_USER"롤을 가진 사용자만 접근 가능
                .antMatchers("/member/info").hasRole("USER")

                // 모든 경로는 권한없이 접근 가능
                .antMatchers("/**").permitAll()

                // 회원가입 페이지 권한없이 접근 가능
//                .antMatchers("/member/member").permitAll()

                // 모든 요청에 대해, 인증된 사용자만 접근하도록 설정(필터)
                .anyRequest().authenticated()

        .and()
            /*
             로그인 페이지 로그인 폼의 아이디, 패스워드를 각각 username, password로
             기본 인식 하기 때문에 폼에서 저렇게 설정 해줘야하고
             바꾸고 싶다면 .usernameParameter("파라미터명")으로 바꿔준다.
             */
            .formLogin()

            // 로그인 폼의 input태그 아이디 username(기본값)을 email로 바꿔준다.
            .usernameParameter("email")
            .passwordParameter("pwd")

            // SpringSecurity가 제공해주는 폼을 사용안할 거면 .loginPage()로 URL 지정
            .loginPage("/member/member")

            // form action의 경로와 일치 시켜줘야 한다.
            .loginProcessingUrl("/member/loginProc")

            // 로그인 성공시 이동할 페이지
            .defaultSuccessUrl("/")
            .permitAll()

        .and()
             // 로그아웃
            .logout()

            // 로그아웃 컨트롤러 매핑
            .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))

            // 로그아웃 성공시 경로
          .logoutSuccessUrl("/")

             // 로그아웃 하면 Session 초기화 해주기
            .invalidateHttpSession(true)

        .and()
            // 권한 없는 대상이 접속 시도 했을때, 403 예외처리 핸들링
            .exceptionHandling().accessDeniedPage("/member/denied");
    }

//     BCrypt 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /*
    http.authenticationProvider는 내부적으로
    AuthenticationManagerBuilder.authenticationProvider를 호출한다.
    AuthenticationManagerBuilder.userDetatilsService().passwordEncoder()를 통해
    패스워드 암호화에 사용될 PasswordEncoder 구현체를 지정한다.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder());
    }

}
