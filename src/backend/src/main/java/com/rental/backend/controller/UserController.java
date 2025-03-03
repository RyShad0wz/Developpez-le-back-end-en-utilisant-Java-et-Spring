package com.rental.backend.controller;

import com.rental.backend.dto.UserDTO;
import com.rental.backend.dto.UserRegistrationRequest;
import com.rental.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService){
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
    UserDTO userDTO = userService.getUserById(id);
    return ResponseEntity.ok(userDTO);
  }

  @PostMapping("/register")
  public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegistrationRequest request){
    // On suppose que UserRegistrationRequest contient name, email et password
    UserDTO dto = new UserDTO();
    dto.setName(request.getName());
    dto.setEmail(request.getEmail());
    UserDTO createdUser = userService.createUser(dto, request.getPassword());
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

  // Vous pouvez ajouter ici un endpoint pour l'authentification (login) si nécessaire.
}

