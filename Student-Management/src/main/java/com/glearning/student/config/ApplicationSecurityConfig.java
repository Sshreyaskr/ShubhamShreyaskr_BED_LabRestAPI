package com.glearning.student.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.glearning.student.service.UserDetailsServiceImpl;

@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/","/students/save","/students/showFormForAdd","/students/403").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
			.antMatchers("/students/showFormForUpdate","/students/delete").hasAuthority("ROLE_ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin().loginProcessingUrl("/login").defaultSuccessUrl("/students/list",true).permitAll()
			.and()
			.logout().logoutSuccessUrl("/login").permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/students/403")
			.and()
			.cors().and().csrf().disable();
	}
	

}
