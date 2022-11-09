package com.example.Spring.boot.project1.config;

import com.example.Spring.boot.project1.jwt.CustomAuthenticationEntryPoint;
import com.example.Spring.boot.project1.jwt.JwtAuthenticationFilter;
import com.example.Spring.boot.project1.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // authenticationManager를 Bean 등록합니다.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/styles.css", "/js/**", "/img/**", "/lib/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 일반적인 루트가 아닌 다른 방식으로 요청시 거절, header에 id, pw가 아닌 token(jwt)을 달고 간다. 그래서 basic이 아닌 bearer를 사용한다.
        // Not to use csrf in order to use REST API.
        http.csrf().disable();

        // Not to use basic login page that spring security supports as default.
        http.httpBasic().disable()

                // Not to use session since we are using JWT.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
                .headers()
                .and()
        /**
         * antMatchers() : 해당 URL로 요청 시 설정을 해준다.
         * authenticated() : andMatchers에 속해있는 URL로 요청이 오면 인증이 필요하다고 설정한다.
         * hasRole() : andMatchers에 속해있는 URL로 요청이 들어오면 권한을 확인한다.
         * addFilterBefore() : 필터를 등록한다. 스프링 시큐리티 필터링에 등록해주어야 하기 때문에, 여기에 등록해주어야 한다.
         *                     파라미터는 2가지가 들어간다. 왼쪽은 커스텀한 필터링이 들어간다. 오른쪽에 등록한 필터전에 커스텀필터링이 수행된다.
         * http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) : 세션을 사용하지 않는다고 설정한다.
         */
                // All paths below down are going to be checked their permission.
                .authorizeRequests()

                .antMatchers("/").permitAll()
                    // Everyone can access to /user path in order to sign up and sign in.
                    .antMatchers("/user/**").permitAll()

                    // Allowing css files as well.
//                    .antMatchers("/styles.css").permitAll()

                    // Other paths requires USER permission to access.
                    .anyRequest().hasRole("USER")

         .and()

                // Customized CustomAuthenticationEntryPoint.
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
         .and()
                // Add JwtAuthenticationFilter before UsernamePasswordAuthenticationFilter.
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        //                .antMatchers("/test").authenticated()
        //                .antMatchers("/chat").authenticated()
        //                .antMatchers("/admin/**").hasRole("ADMIN")
        //                .antMatchers("/user/**").hasRole("USER")
        //                .antMatchers("/**").permitAll()
    }
}