package dev.bahner.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user1").password(passwordEncoder().encode("pass1")).roles("USER")
		.and().withUser("user2").password(passwordEncoder().encode("pass2")).roles("USER")
		.and().withUser("admin").password(passwordEncoder().encode("pword")).roles("ADMIN");
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/anonymous*").anonymous()
		.antMatchers("/login*").authenticated()
		.and().formLogin().loginPage("/login.htlm")
		.loginProcessingUrl("/perform_login")
		.defaultSuccessUrl("/homepage.html", true)
		.failureUrl("/login.html?error=true")
		.and().logout().logoutUrl("/perform_logout")
		.deleteCookies("JSESSIONID");
	}
}
