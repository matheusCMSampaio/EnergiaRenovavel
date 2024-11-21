package br.com.fiap.EnergiaRenovavel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/**" ,"/alertas").permitAll()
                        .requestMatchers("/user/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form.loginPage("/Login")
                        .defaultSuccessUrl("/Index")
                        .failureUrl("/Login?falha=true")
                        .permitAll())
                .logout((logout) ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout=true")
                                .permitAll())
                .exceptionHandling((exception) ->
                        exception.accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.sendRedirect("/acesso_negado");
                        }));

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
