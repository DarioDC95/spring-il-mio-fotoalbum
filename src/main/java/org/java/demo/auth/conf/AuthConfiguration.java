package org.java.demo.auth.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfiguration {

	@Bean
	PasswordEncoder passwordEncoder() {
		
//	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    
		return 
			http.csrf(c -> c.disable()).authorizeHttpRequests(a -> a
					.requestMatchers("/picture/index").hasAnyAuthority("ADMIN", "SUPER_ADMIN")
					.requestMatchers("/picture/by/title").hasAnyAuthority("ADMIN", "SUPER_ADMIN")
					.requestMatchers("/picture/edit/{id}/super_admin").hasAuthority("SUPER_ADMIN")
			        .requestMatchers("/picture/**").hasAuthority("ADMIN")
			        .requestMatchers("/category/**").hasAuthority("ADMIN")
			    
			        .requestMatchers("/**").permitAll()
			).formLogin(f -> f.permitAll()
			).logout(l -> l.logoutSuccessUrl("/")
			).exceptionHandling(e -> e
		            .accessDeniedPage("/") // Specify the access-denied page URL

			).build();
	}
}
