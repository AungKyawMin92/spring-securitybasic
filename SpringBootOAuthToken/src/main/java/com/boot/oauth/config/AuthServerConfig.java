package com.boot.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import com.boot.oauth.service.CustomUserDetailService;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired AuthenticationManager authenticationManager;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	/*@Autowired
    private PasswordEncoderConfig passwordEncoderCfg;*/
	
	@Autowired CustomUserDetailService usd;
	
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
        .pathMapping("/oauth/token", "/oauth/mtoken");
		endpoints.authenticationManager(authenticationManager);
		
		
		
		/*endpoints
        .approvalStore(approvalStore())
        .authorizationCodeServices(authorizationCodeServices())
        .tokenStore(tokenStore());
*/
		
	}
	
	/*public AuthenticationManager authenticationManagerBean() throws Exception {
		return authenticationManagerBean();
	}*/
	
/*	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("auth auth");
        auth.userDetailsService(usd)
        .passwordEncoder(passwordEncoder);
    }*/
	
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		/*clients.inMemory()
		.withClient("clientId")
		.authorizedGrantTypes("grandType")
		.scopes("scopr")
		.authorities("authorize")
		.resourceIds("resourceId")
		.accessTokenValiditySeconds(500)
		.secret("secret");*/
		
		 clients
         .inMemory()
         .withClient("my-trusted-client")
         .authorizedGrantTypes("client_credentials", "password")
         .authorities("ROLE_CLIENT","ROLE_TRUSTED_CLIENT")
         .scopes("read","write","trust")
         .resourceIds("oauth2-resource")
         .accessTokenValiditySeconds(80)
         .secret(passwordEncoder.encode("secret"));
	}
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {		
		security.passwordEncoder(passwordEncoder);
		
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}
	
	
}
