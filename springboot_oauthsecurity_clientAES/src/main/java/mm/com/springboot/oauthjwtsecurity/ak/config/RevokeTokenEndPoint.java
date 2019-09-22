package mm.com.springboot.oauthjwtsecurity.ak.config;

import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RevokeTokenEndPoint {
	
	@Resource(name="tokenServices")
	private ConsumerTokenServices tokenService;
	
	@Autowired
	private TokenStore tokenStore;
	
	@DeleteMapping(value="mr.bean/logout")
	public void revokeToken(HttpServletRequest request) {
		System.out.println("revoke token");
		String authorization = request.getHeader("Authorization");
		if(authorization != null && authorization.contains("Bearer")) {
			String loginToken = authorization.substring("Bearer".length()+1);
			tokenService.revokeToken(loginToken);
			
			/*OAuth2AccessToken assTkn = tokenStore.readAccessToken(loginToken);
			if(assTkn != null) {
				System.out.println("token is "+assTkn);
				tokenStore.removeAccessToken(assTkn);
			}*/
			
		//	Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId("angular-client-id"); 
		//	System.out.println("tokens "+tokenService.getClientId(loginToken));
			/*
			System.out.println("tkn"+loginToken);
			System.out.println("aa"+tokens);
			
			tokenService.revokeToken(loginToken);*/
			
			/*Collection<OAuth2AccessToken> tokena = tokenStore.findTokensByClientIdAndUserName("angular-client-id","ak");			
			 if (tokena!=null){
			        for (OAuth2AccessToken token:tokena){
			            
			        	
			        	System.out.println("token.ACCESS_TOKEN"+token.getValue());
			        	System.out.println("cur "+loginToken);
			        	
			        	
			        		tokenStore.removeAccessToken(token);
			        		System.out.println("remove the token");
			        		
			        	
			        }
			    }*/
			    
			
			
		  /*  if (tokens!=null){
		        for (OAuth2AccessToken token:tokens){
		            
		        	
		        	System.out.println("token.ACCESS_TOKEN"+token.getValue());
		        	System.out.println("cur "+loginToken);
		        	
		        	
		        	if(token.getValue().equals(loginToken)) {
		        		tokenStore.removeAccessToken(token);
		        		System.out.println("remove the token");
		        	}
		        		
		        	
		        }
		    }
		    */
		   
			
			//tokenStore.removeAccessToken(token);
			//String token = authorization.substring("Bearer".length()+1);
			
			System.out.println("revoke");
		}				
	}
}
