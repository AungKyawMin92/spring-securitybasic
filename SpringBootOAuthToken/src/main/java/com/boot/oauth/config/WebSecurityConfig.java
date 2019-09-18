package com.boot.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(5)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
/*	@Autowired
    private PasswordEncoder passwordEncoder;
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/oauth/mtoken").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic().and().csrf().disable();
		
		/*http.httpBasic().and().authorizeRequests().antMatchers("/*").hasAnyRole("ADMIN","USER").and().formLogin().permitAll()
		.loginProcessingUrl("/oauth/mtoken")
        .usernameParameter("userId")
        .passwordParameter("password")
        .and().httpBasic().and().csrf().disable();*/
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	
/*	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceBean())
        .passwordEncoder(passwordEncoder);
    }*/

	
	/* @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    @Override
	    public UserDetailsService userDetailsServiceBean() throws Exception {
	        return new JdbcUserDetails();
	    }

	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/webjars/**");

	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .authorizeRequests()
	                .antMatchers("/login","/logout.do").permitAll()
	                .antMatchers("/**").authenticated()
	                .and()
	                .formLogin()
	                .loginProcessingUrl("/login.do")
	                .usernameParameter("username")
	                .passwordParameter("password")
	                .loginPage("/login")
	                .and()
	                .logout()
	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout.do"))
	                .and()
	                .userDetailsService(userDetailsServiceBean());
	    }

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsServiceBean())
	        .passwordEncoder(passwordEncoder());
	    }*/


}
