package com.demo.monolithic_spring_security_product_demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    // builder design pattern
    @Bean
    public UserDetailsService userDetailsService(/*must done the password encoder*/PasswordEncoder encoder) { 
    	UserDetails admin = User.withUsername("viswa")
    					.password(encoder.encode("123"))
    					.roles("ADMIN")
    					.build();
    	UserDetails user = User.withUsername("user")
    					.password(encoder.encode("123"))
    					.roles("USER")
    					// .roles("USER, ADMIN")
    					.build();
    	return new InMemoryUserDetailsManager(admin, user);
    }

	// by defalut spring expect you to set password encoder
    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth.requestMatchers("/swagger-ui/**","/h2-console/**") // mentioning those to excluded
		.permitAll()
		.anyRequest() // any other request
		.authenticated())
		.formLogin(Customizer.withDefaults())
		.headers((headers)->headers.frameOptions((frame) -> frame.sameOrigin()))
		.build();
	}
	
}

