package med.voll.web_application.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public UserDetailsService RegistratedUsersData() {
        UserDetails user1 = User.builder().username("user1@email.com").password("{noop}password1") // {noop} indicates no password encoder
                .build();

        UserDetails user2 = User.builder().username("user2@email.com").password("{noop}password2") // {noop} indicates no password encoder
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(req -> {
                    req.requestMatchers("/css/**", "/js/**", "/assets/**").permitAll();
                    req.anyRequest().authenticated();
                }).formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/").permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll()) // permite o logout para qualquer usuário
                .build();
    }

}
