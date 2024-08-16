package com.mani.RegisterationAndLogin.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> {
            try {
                csrf.disable()
                        .authorizeRequests(a -> a
                                .requestMatchers("/","/login","/register","/oauth2/authorization/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()

                        )
                        .formLogin(a->
                                a.loginPage("/loginpage")
                                        .permitAll())
                        .logout(
                                a->a.logoutUrl("/logout")
                                        .logoutSuccessUrl("/")
                                        .permitAll()
                        );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).oauth2Login(a->a
                .defaultSuccessUrl("/welcome",true)
                .failureUrl("/login")
        );
        return http.build();
    }
}
