package mm.com.springboot.oauthjwtsecurity.ak.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${security.oauth.jwt.client-id}")
	private String clientId;

	@Value("${security.oauth.jwt.client-secret}")
	private String clientSecret;

	@Value("${security.oauth.jwt.grant-type}")
	private String grantType;

	@Value("${security.oauth.jwt.scope-read}")
	private String scopeRead;

	@Value("${security.oauth.jwt.scope-write}")
	private String scopeWrite;

	@Value("${security.oauth.jwt.resource-ids}")
	private String resourceIds;

	//comment for inMemoryTokenStore
	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired private DataSource dataSource;// for token store in db
		
	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		configurer//.jdbc(dataSource)
		        .inMemory()
		        .withClient(clientId)
				.secret(clientSecret)
		        .authorizedGrantTypes(grantType)
		        .scopes(scopeRead, scopeWrite)
		        .accessTokenValiditySeconds(95)
		    //    .refreshTokenValiditySeconds(95)
		        .resourceIds(resourceIds);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
		endpoints.pathMapping("/oauth/token", "/mr.bean/access");
		endpoints.tokenStore(tokenStore)
		        .accessTokenConverter(accessTokenConverter)
		        .tokenEnhancer(enhancerChain)
		        .authenticationManager(authenticationManager);
		//comment for inMemoryTokenStore
		/*endpoints.tokenStore(tokenStore)
        .accessTokenConverter(accessTokenConverter)
        .tokenEnhancer(enhancerChain)
        .authenticationManager(authenticationManager);*/		
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}
}