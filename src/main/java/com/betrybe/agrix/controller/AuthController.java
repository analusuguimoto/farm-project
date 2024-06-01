package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.AuthenticatorDto;
import com.betrybe.agrix.controller.dto.TokenDto;
import com.betrybe.agrix.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Auth Controller with its methods.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService service;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager,
      TokenService service) {
    this.authenticationManager = authenticationManager;
    this.service = service;
  }

  /**
   * Post route for creating a token and authentication.
   */
  @PostMapping("/login")
  public TokenDto login(@RequestBody AuthenticatorDto authenticatorDto) {
    UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(
        authenticatorDto.username(), authenticatorDto.password());

    Authentication authentication = authenticationManager.authenticate(userPass);
    String tokenCreate = service.createToken(authentication.getName());

    return new TokenDto(tokenCreate);
  }
}
