package ru.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.event.entity.Role;
import ru.event.entity.User;
import ru.event.repositroy.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;

@RestController
public class RegistrationController {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  HttpSession httpSession;
  @Autowired
  protected AuthenticationManager authenticationManager;

  public RegistrationController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, HttpSession httpSession) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.httpSession = httpSession;
  }

  @PostMapping("/api/v1/signUp")
  public String registration(@RequestBody User user, HttpServletRequest request) {
    if (userRepository.findByUsername(user.getUsername()) != null) {
      return "user exists";
    }
    String password = user.getPassword();
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setActive(true);
    user.setRoles(Collections.singleton(Role.USER));
    userRepository.save(user);
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), password);
    token.setDetails(new WebAuthenticationDetails(request));
    Authentication authentication = authenticationManager.authenticate(token);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return "true";
  }
}
