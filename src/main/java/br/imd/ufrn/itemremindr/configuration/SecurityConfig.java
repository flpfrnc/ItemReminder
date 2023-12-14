package br.imd.ufrn.itemremindr.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorized -> authorized
                        // public endpoints
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        // private endpoints
                        .requestMatchers(HttpMethod.POST, "/items").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/items/{id}", "/items/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/items", "/items/{id}", "/items/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/items/{id}", "/items/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/places").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/places/{id}", "/place/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/places/{placeId}/item/{itemId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/places", "/places/{id}", "/place/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/places/{id}", "/place/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
