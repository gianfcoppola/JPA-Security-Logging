package com.example.progettoCompleto.demo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.progettoCompleto.demo.repository.StudentRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private UserPrincipalDetailsService userPrincipalDetailsService;
	private StudentRepository studentRepository;
	
	

	public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService,
			StudentRepository studentRepository) {
		super();
		this.userPrincipalDetailsService = userPrincipalDetailsService;
		this.studentRepository = studentRepository;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
        // remove csrf and state in session because in jwt we do not need them
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        // add jwt filters (1. authentication, 2. authorization)
        .addFilter(new JWTAuthenticationFilter(authenticationManager()))
        .addFilter(new JWTAuthorizationFilter(authenticationManager(),  this.studentRepository))
        .authorizeRequests()
        // configure access rules
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers(HttpMethod.POST, "/studenti").permitAll()
        .antMatchers("/corsi/**").permitAll()
        .antMatchers("/studenti/**").authenticated()
        .antMatchers("/esami/**").authenticated()
        //.antMatchers("/api/public/management/*").hasRole("MANAGER")
        //.antMatchers("/api/public/admin/*").hasRole("ADMIN")
        .anyRequest().authenticated();
	}
	
	
	@Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
}
