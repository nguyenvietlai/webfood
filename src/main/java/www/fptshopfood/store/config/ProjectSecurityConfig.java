package www.fptshopfood.store.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.ignoringRequestMatchers("/rest/**","/register/**"))
                .authorizeHttpRequests((requests) -> requests.requestMatchers("/cart").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        .requestMatchers("/home" , "/" , "").permitAll()
                        .requestMatchers("/register/**").permitAll()


                        .requestMatchers("/checkout").hasAnyRole("USER" , "ADMIN")
                         .requestMatchers("/order-history").hasAnyRole("USER" , "ADMIN")

                        .requestMatchers("/contact").authenticated()
                        .requestMatchers("/cart").hasRole("USER")
                        .requestMatchers("/about").permitAll()

                        .requestMatchers("/assets/**","/css/**",
                                "/icons/**","/images/**","/js/**" , "/vendor/**").permitAll()

                        .requestMatchers("/detail_product").permitAll()
                        .requestMatchers("/errorzz").permitAll()

                        .requestMatchers("/new").permitAll()
                        .requestMatchers("/product").permitAll()
                        .requestMatchers("/single-new").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/rest/**").permitAll()
                        .requestMatchers("/oauth2/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()

                )


                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .defaultSuccessUrl("/home").failureUrl("/login?error=true").permitAll())
                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());


        http.oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
                .defaultSuccessUrl("/oauth2/login/success", true)
                .failureUrl("/auth/login/error")
                .authorizationEndpoint(authorization -> authorization
                        .baseUri("/oauth2/authorization")
                )
        );


//        http.headers(headersConfigurer -> headersConfigurer
//                .frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));

        return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}