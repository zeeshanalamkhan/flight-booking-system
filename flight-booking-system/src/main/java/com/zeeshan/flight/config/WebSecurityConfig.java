package com.zeeshan.flight.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(proxyTargetClass = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserDetailsService userService;

	/*
	 * @Autowired private DataSource dataSource;
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.headers().frameOptions().sameOrigin().and().authorizeRequests()
				.antMatchers("/", "/flight/search", "/flight/book/verify", "/flight/book/cancel").permitAll()
				.antMatchers("/flight/book**", "/flight/book/new").hasRole("AGENT").antMatchers("/**").hasRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/")
				.failureUrl("/login?error").permitAll().and().logout().logoutSuccessUrl("/login?logout").permitAll()
				.and().csrf().disable().exceptionHandling();
	}

	@Bean
	public SpringSecurityDialect securityDialect() {

		return new SpringSecurityDialect();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

}
