package com.voiceofsiren.toy0002.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /*
        http    // CSRF (Cross-Site Request Forgery): 사용자가 원하지 않아도 서버 측으로 위조된 요청을 강제로 보내는 해킹 방법
                .csrf(AbstractHttpConfigurer::disable);     // 배포 시 enable시켜야 함.
        */

        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/account/register","/css/**", "/api/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/account/login")    // login 시 redirect 경로
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/")          // logout 시 redirect 경로
                        .permitAll());

        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)     // 하나의 id로 동시 접속할 수 있는 최대 세션 수를 지정 (다중 로그인 설정)
                        .maxSessionsPreventsLogin(true));   // 다중 로그인 허용 개수를 초과하였을 경우
                                                            //      true: 새로운 로그인을 차단
                                                            //      false: 기존의 세션을 하나씩 삭제

        http
                .sessionManagement((session) -> session
                        .sessionFixation()      // 세션 고정 공격 보호를 위한 로그인 성공 시 세션 설정 방법
                                .changeSessionId());        // changeSessionId(): 로그인 시 동일한 세션에 대한 id를 변경
                                                            // newSession(): 로그인 시 새로운 세션을 생성
                                                            // none(): 로그인 시 세션 정보를 변경하지 않음

        return http.build();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled "
                        + "from user "
                        + "where username = ?")
                .authoritiesByUsernameQuery("select u.username, r.name "
                        + "from user_role ur "
                        + "inner join user u on ur.user_id = u.user_id "
                        + "inner join role r on ur.role_id = r.role_id "
                        + "where u.username = ?");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     *  In-Memory 방식으로 데이터를 저장
     */
    /*
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                        .username("admin1")
                        .password(bCryptPasswordEncoder().encode("1234"))
                        .roles("ROLE_ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
    */
}