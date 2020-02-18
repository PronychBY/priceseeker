package com.senla.pronych.hibernate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.senla.pronych.hibernate.security.jwt.JwtAuthEntryPoint;
//import com.senla.pronych.hibernate.security.jwt.JwtAuthEntryPoint;
import com.senla.pronych.hibernate.security.jwt.JwtAuthTokenFilter;
import com.senla.pronych.hibernate.security.service.UserDetailsServiceImpl;

@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"com.senla.pronych.hibernate.*"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	};	
	
	@Autowired
	private JwtAuthEntryPoint unauthorizedHandler;
	
	@Bean
	public JwtAuthTokenFilter authenticationJwtTokenFilter() {
		return new JwtAuthTokenFilter();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	   
		/*http
        .authorizeRequests(authorizeRequests ->
            authorizeRequests
                .anyRequest().authenticated()
        )
        .httpBasic();*/
		
		http.cors().and().csrf().disable()
		.authorizeRequests().antMatchers("/api/auth/**").permitAll()
		.antMatchers("/api/test/pm").hasRole("")
		.antMatchers("/price/**").permitAll()
		.antMatchers("/user/**").permitAll()
		.antMatchers("/product/**").permitAll()
		.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()		
		.anyRequest().authenticated()
		.and()
		.httpBasic()
		.and()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		.and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        		
		
		 
		/*http
		 .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
         .httpBasic()
         .and()
         .csrf()
         .disable();*/		
		
		//.authorizeRequests().antMatchers("/**").hasRole("ADMIN")        //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		/*http.cors().and().csrf().disable()
	           //.authorizeRequests().antMatchers("/api/auth/**").permitAll()
	           //.and()
	           .authorizeRequests().antMatchers("/api/test/user/**").hasRole("ADMIN")
	           .and()
	           .authorizeRequests().antMatchers("/api/test/pm/**").hasRole("PM")
	           .and()
	           .authorizeRequests().anyRequest().authenticated()
	           .and()
	           .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	           .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
	        
	   http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}