package com.shimbhu.security;


import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.shimbhu.security.jwt.JwtGeneratorFilter;
import com.shimbhu.security.jwt.JwtValidationFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain mySecurityConfig(HttpSecurity http) throws Exception
	{
	// CORS configuration
		http.sessionManagement( sessionmangement ->  sessionmangement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.cors(cors -> {
			cors.configurationSource(new CorsConfigurationSource() {
				
				@Override
				public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
					
					CorsConfiguration cfg = new CorsConfiguration();
					cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
					cfg.setAllowedMethods(Collections.singletonList("*"));
					cfg.setAllowCredentials(true);
					cfg.setAllowedHeaders(Collections.singletonList("*"));
					cfg.setExposedHeaders(Arrays.asList("Authorization"));
					
				   return cfg;
					
				}
			});
		})
			
		.authorizeHttpRequests(
				(auth)-> auth
				.requestMatchers(HttpMethod.POST,"/users").permitAll()
				.requestMatchers(HttpMethod.GET,"/tweets").permitAll()
				.requestMatchers("/v3/api-docs/**", "/swagger-ui*/**", "/swagger-ui.html").permitAll()
				.requestMatchers(HttpMethod.GET,"/users","/retweets").hasRole("ADMIN")
				.requestMatchers("/users/ban-user/**").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET,"/sign-in").hasAnyRole("ADMIN","BANNED_USER","USER")
				.requestMatchers(HttpMethod.GET,"/users/**","/welcome","/tweets/**","/retweets/**","/followers/**").hasAnyRole("ADMIN","USER")
				.anyRequest().authenticated()
				)
		.csrf(csrf -> csrf.ignoringRequestMatchers("/**")
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				)
		.addFilterAfter(new JwtGeneratorFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtValidationFilter(), BasicAuthenticationFilter.class)
		.httpBasic(Customizer.withDefaults())
		.formLogin(Customizer.withDefaults());
		
		 return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
