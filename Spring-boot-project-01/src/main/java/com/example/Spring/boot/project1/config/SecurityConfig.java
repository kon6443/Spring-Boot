package com.example.Spring.boot.project1.controllers;

import com.example.Spring.boot.project1.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/");
    }

    @Override
    public void configure (HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)


                /**
                 * h2 console을 위한 설정 추가
                */
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                /**
                 * session을 사용하지 않기 때문에 session 설정을 STATELESS로 지정.
                 */
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)


                /**
                 * 아래 해당 경로는 토큰이 없는 상태에서 요청이 들어오기 때문에 열어둠.
                 * anyRequest().authenticated()로 나머지 요청은 인가 필요.
                 */
                .and()
                .authorizeRequests()
                .antMatchers("/user").permitAll()
                .anyRequest().authenticated()

                /**
                 * JwtSecurityConfig 클래스 적용.
                 */
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
}
