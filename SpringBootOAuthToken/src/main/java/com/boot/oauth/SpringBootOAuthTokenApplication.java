package com.boot.oauth;


import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.boot.oauth.model.RoleEntity;
import com.boot.oauth.model.UserEntity;
import com.boot.oauth.service.UserService;

@SpringBootApplication
public class SpringBootOAuthTokenApplication {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }   

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOAuthTokenApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner setupDefaultUser(UserService service) {
        return args -> {
            service.save(new UserEntity(
            		"user", 
                    "user", 
                    Arrays.asList(new RoleEntity("USER"), new RoleEntity("ACTUATOR")),//roles 
                    true//Active
            ));
        };
    }

}
