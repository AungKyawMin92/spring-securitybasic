package com.boot.oauth.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping(value="/")
	public String home() {
		
		/*for(int i=0; i< 900 ; i++)
			log.info("in home method");*/
		return "home";
	}
	@GetMapping(value="/private")
	public String privateMethod() {
		
		return "privateMethod";
	}

	

    @GetMapping(value = "/username")
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
}
