package com.AbdoHalim.Ecommerce.Security;

import com.AbdoHalim.Ecommerce.Service.CustomUserDetailsService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Config {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        http.cors(c->c.disable());
        http.csrf(csrf->csrf.disable());
        http.authorizeHttpRequests(auth->{
            auth.requestMatchers("/public/register").permitAll();
            auth.requestMatchers("/public/**").permitAll();
            auth.requestMatchers("/user/**").hasAnyAuthority("USER","ADMIN","TOPADMIN","BRAND");
            auth.requestMatchers("/admin/**").hasAnyAuthority("ADMIN","TOPADMIN");
            auth.requestMatchers("/topadmin/**").hasAuthority("TOPADMIN");
            auth.anyRequest().authenticated();

        });
        http.formLogin(form->{
            form.usernameParameter("email");
        });

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder(11);
    }
    @Bean
    public AuthenticationProvider authentication(){
        DaoAuthenticationProvider provider=new  DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }


}
