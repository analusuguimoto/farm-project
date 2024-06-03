package com.betrybe.agrix.security;

import com.betrybe.agrix.service.PersonService;
import com.betrybe.agrix.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Creating Jwt filter.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
  private final PersonService personService;
  private final TokenService tokenService;

  @Autowired
  public JwtFilter(PersonService personService, TokenService tokenService) {
    this.personService = personService;
    this.tokenService = tokenService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    Optional<String> token = extractToken(request);

    if (token.isPresent()) {
      String subject = tokenService.tokenValidation(token.get());

      UserDetails userDetails = personService.loadUserByUsername(subject);

      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
          userDetails, null, userDetails.getAuthorities()
      );

      SecurityContextHolder.getContext().setAuthentication(auth);
    }

    filterChain.doFilter(request, response);

  }

  private Optional<String> extractToken(HttpServletRequest request) {
    String headerAuthorization = request.getHeader("Authorization");
    if (headerAuthorization == null) {
      return Optional.empty();
    }

    return Optional.of(headerAuthorization.replace("Bearer ", ""));
  }
}
