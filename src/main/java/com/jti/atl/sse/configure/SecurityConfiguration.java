package com.jti.atl.sse.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				String name = "admin";
				String password = "admin";
				String roles = "ADMIN";
				if(!username.equals(name)) {
					name = "admin";
					password = "password";
					roles = "USER";
				}
				return new User(name, getPasswordEncoder().encode(password), true, true, true, true,
						AuthorityUtils.createAuthorityList(roles));
			}
		};
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.exceptionHandling()
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/movies/**").permitAll()
			.antMatchers(HttpMethod.POST, "/movies/{\\d+}/scores**").hasAuthority("USER")
			.antMatchers(HttpMethod.DELETE, "/movies/{\\d+}/scores/{\\d+}").hasAuthority("USER")
			.antMatchers(HttpMethod.POST, "/movies**").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.PUT, "/movies/{\\d+}").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/movies/{\\d+}").hasAuthority("ADMIN")
			.and().httpBasic();
	}

	@Bean
	@Autowired
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(getPasswordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
//		auth
//			.inMemoryAuthentication()
//			.withUser("user").password(getPasswordEncoder().encode("password")).roles("USER")
//			.and()
//			.withUser("admin").password(getPasswordEncoder().encode("admin")).roles("ADMIN");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
