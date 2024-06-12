package com.example.KT_Giua_Ky.config;

//import com.example.SpringLogin.service.UserDetailsServiceImpl;
import com.example.KT_Giua_Ky.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain configure (HttpSecurity http) throws Exception {

        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeRequests(authorizeRequests ->
                            authorizeRequests
                                    .requestMatchers("/","/login","/logout").permitAll()
                                    .requestMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                                    .requestMatchers("/admin").access("hasRole('ROLE_ADMIN')")
                                    .anyRequest().authenticated()

                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .accessDeniedPage("/403")
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginProcessingUrl("/j_spring_security_check")
                                .loginPage("/login")
                                .defaultSuccessUrl("/userInfo")
                                .failureUrl("/login?error=true")
                                .usernameParameter("username")
                                .passwordParameter("password")
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/logoutSuccessful")
                );

        return http.build();
    }
}
