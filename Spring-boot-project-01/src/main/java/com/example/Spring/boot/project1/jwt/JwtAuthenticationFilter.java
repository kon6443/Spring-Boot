package com.example.Spring.boot.project1.jwt;

import com.example.Spring.boot.project1.docs.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// 해당 클래스는 JwtTokenProvider가 검증을 끝낸 Jwt로부터 유저 정보를 조회해와서 UserPasswordAuthenticationFilter 로 전달합니다.
/**
 * This class checks if the JWT token validates and if so, pass the user information to UserPasswordAuthenticationFilter.
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Receiving JWT token from the header.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        System.out.println("doFilter token: " + token);
        // Checks token if it validates.
        if (token!=null && jwtTokenProvider.validateToken(token)) {
            // Get the user information if the token validates.
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            // Save the authentication object into the SecurityContext.
            SecurityContextHolder.getContext().setAuthentication(authentication);
//            User temp = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            System.out.println("temp: " + temp.getAddress());
        }
        chain.doFilter(request, response);
    }
}
