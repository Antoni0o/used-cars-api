package com.usedcarsapi.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usedcarsapi.security.dtos.LoginRequestDTO;
import com.usedcarsapi.security.services.TokenService;
import com.usedcarsapi.user.User;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class AuthController {

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public String login(@RequestBody LoginRequestDTO request) {
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getName(),
        request.getPassword());

    Authentication auth = this.authManager.authenticate(authToken);

    User user = (User) auth.getPrincipal();

    return tokenService.generateToken(user);
  }
}
