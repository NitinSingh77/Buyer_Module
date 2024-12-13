package com.UsedCarSellingAndRental.app.SpringApp;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.UsedCarSellingAndRental.app.SpringApp.service.UserSecurityService;
 
@Configuration
public class SecurityConfig {

	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		 .csrf((csrf) -> csrf.disable())
		 .authorizeHttpRequests(authorize -> authorize
				 	.requestMatchers(HttpMethod.GET, "/api/token").permitAll()
				 	.requestMatchers(HttpMethod.GET, "/api/car/one").permitAll()
				 	.requestMatchers(HttpMethod.GET, "/api/show/car/all").hasAuthority("BUYER")
				 	.requestMatchers(HttpMethod.GET, "/api/car/all/filter/suvEv").hasAuthority("BUYER")
				 	.requestMatchers(HttpMethod.GET, "/api/car/all/filter/suvpetrol").hasAuthority("BUYER")
				 	.requestMatchers(HttpMethod.GET, "/api/car/all/filter/suvDiesel").hasAuthority("BUYER")
				 	.requestMatchers(HttpMethod.GET, "/api/car/all/filter/suvHybrid").hasAuthority("BUYER")
				 	.requestMatchers(HttpMethod.GET, "/api/car/all/filter/sedanEv").hasAuthority("BUYER")
				 	.requestMatchers(HttpMethod.GET, "/api/car/all/filter/sedanpetrol").hasAuthority("BUYER")
				 	.requestMatchers(HttpMethod.GET, "/api/car/all/filter/sedandiesel").hasAuthority("BUYER")
				 	.requestMatchers(HttpMethod.GET, "/api/car/all/filter/sedanhybrid").hasAuthority("BUYER")
				 	.requestMatchers(HttpMethod.GET, "/api/car/all/sold").hasAuthority("BUYER")
				 	.requestMatchers(HttpMethod.GET, "/auth/user").authenticated()
				 	.requestMatchers(HttpMethod.GET, "/api/fetch/all/carqueries").permitAll()
				 	.requestMatchers(HttpMethod.GET, "/api/car/all/sold").permitAll()
				 	.requestMatchers(HttpMethod.GET, "/api/get/all/car/byname").permitAll()
				 	 .requestMatchers(HttpMethod.POST, "/insert/bought_car/details").permitAll()
				 	 .requestMatchers(HttpMethod.POST, "/auth/sigh-up/buyer").permitAll() 
				 	.requestMatchers(HttpMethod.POST, "/api/car/image/upload/{pid}").authenticated() 
				 	 .requestMatchers(HttpMethod.POST, "/api/buyer/update").hasAuthority("BUYER")
				 	.requestMatchers(HttpMethod.POST, "/api/add/seller").permitAll() 
				 	.requestMatchers(HttpMethod.POST, "/api/car/add").permitAll() 
				 	.requestMatchers(HttpMethod.POST, "/api/cars/add/batchAPI").permitAll() 
				 	.requestMatchers(HttpMethod.POST, "/insert/bought_car/details/").permitAll() 
				 	.requestMatchers(HttpMethod.POST, "/carQueries/add/{cId}/{bId}").hasAuthority("BUYER")
				 	
				 	 	 
				 	
				.anyRequest().permitAll()
			) 
			.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			
		   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		 
		return http.build();
	}
	
	
	@Bean
	PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userSecurityService);
		authenticationProvider.setPasswordEncoder(getEncoder());
		return authenticationProvider;
	}
}

/*
 * Spring needs a middleware to understand and decode jwt token 
 * 
 */