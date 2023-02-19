package com.ak.spring.jwt.controller;

import com.ak.spring.jwt.model.JwtRequest;
import com.ak.spring.jwt.model.JwtResponse;
import com.ak.spring.jwt.service.UserService;
import com.ak.spring.jwt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil JwtUtil;

    @GetMapping("/")
    public String home(){
        return "Welcome !!!!";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUserName());

        final String token =
                JwtUtil.generateToken(userDetails);

        return  new JwtResponse(token);
    }
}
