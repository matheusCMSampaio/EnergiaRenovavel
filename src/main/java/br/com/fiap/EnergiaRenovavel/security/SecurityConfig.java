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
                .authorizeHttpRequests((requests) ->
                        requests
                                .requestMatchers("/api/user", "/api/user/all", "/api/user/{id}", "/api/user/save", "/api/user/delete/{id}", "/api/user/update/{id}")  // Endpoints de usuário
                                .hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/api/dispositivo", "/api/dispositivo/all", "/api/dispositivo/{id}", "/api/dispositivo/save", "/api/dispositivo/delete/{id}", "/api/dispositivo/update/{id}") // Endpoints de dispositivo
                                .hasAuthority("ROLE_ADMIN")
                                .anyRequest()
                                .authenticated())
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
