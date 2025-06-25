package med.voll.web_application.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public UserDetailsService RegistratedUsersData() {
        UserDetails user1 = User.builder().username("user1")
                .password("{noop}password1") // {noop} indicates no password encoder
                .build();

        UserDetails user2 = User.builder().username("user2")
                .password("{noop}password2") // {noop} indicates no password encoder
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

}
