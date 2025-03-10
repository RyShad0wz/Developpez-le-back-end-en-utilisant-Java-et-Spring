package com.rental.backend.controller;

import com.rental.backend.dto.*;
import com.rental.backend.repository.UserRepository;
import com.rental.backend.service.AuthService;
import com.rental.backend.service.UserService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Schema(description = "Endpoints pour l'authentification")
public class AuthController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  private final AuthService authService;

  public AuthController(AuthService authService){
    this.authService = authService;
  }

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
    var response = authService.register(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
    var response = authService.login(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/me")
  public ResponseEntity<UserDTO> getMe() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    UserDTO userDTO = userService.getUserByEmail(email);
    return ResponseEntity.ok(userDTO);
  }
}
