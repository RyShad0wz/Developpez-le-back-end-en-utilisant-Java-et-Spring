package com.rental.backend.controller;

import com.rental.backend.dto.*;
import com.rental.backend.repository.UserRepository;
import com.rental.backend.service.AuthService;
import com.rental.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints pour l'authentification")
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
  @Operation(summary = "Enregistrer un nouvel utilisateur", description = "Créer un nouvel utilisateur avec les informations fournies")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Utilisateur enregistré avec succès", content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
    @ApiResponse(responseCode = "400", description = "Requête invalide")
  })
  public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request){
    var response = authService.register(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/login")
  @Operation(summary = "Connecter un utilisateur", description = "Authentifier un utilisateur avec ses identifiants")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Utilisateur connecté avec succès", content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
    @ApiResponse(responseCode = "401", description = "Non autorisé")
  })
  public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest request){
    var response = authService.login(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/me")
  @Operation(summary = "Obtenir les informations de l'utilisateur connecté", description = "Récupérer les informations de l'utilisateur actuellement connecté")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Informations de l'utilisateur récupérées avec succès", content = @Content(schema = @Schema(implementation = UserDTO.class))),
    @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
  })
  public ResponseEntity<UserDTO> getMe() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    UserDTO userDTO = userService.getUserByEmail(email);
    return ResponseEntity.ok(userDTO);
  }
}
