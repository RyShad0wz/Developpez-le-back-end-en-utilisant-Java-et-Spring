package com.rental.backend.controller;

import com.rental.backend.dto.UserDTO;
import com.rental.backend.dto.UserRegistrationRequest;
import com.rental.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Endpoints pour la gestion des utilisateurs")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  @Operation(summary = "Récupérer un utilisateur par son ID", description = "Récupère les détails d'un utilisateur spécifique")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Utilisateur trouvé",
      content = @Content(schema = @Schema(implementation = UserDTO.class))),
    @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé"),
    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
  })
  public ResponseEntity<UserDTO> getUser(
    @Parameter(description = "ID de l'utilisateur", example = "1", required = true)
    @PathVariable Long id
  ) {
    UserDTO userDTO = userService.getUserById(id);
    return ResponseEntity.ok(userDTO);
  }

  @PostMapping("/register")
  @Operation(summary = "Créer un nouvel utilisateur", description = "Enregistre un nouvel utilisateur avec les informations fournies")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Utilisateur créé avec succès",
      content = @Content(schema = @Schema(implementation = UserDTO.class))),
    @ApiResponse(responseCode = "400", description = "Données de l'utilisateur invalides"),
    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
  })
  public ResponseEntity<UserDTO> registerUser(
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Données de l'utilisateur à créer",
      required = true,
      content = @Content(schema = @Schema(implementation = UserRegistrationRequest.class)))
    @Valid @RequestBody UserRegistrationRequest request
  ) {
    UserDTO dto = new UserDTO();
    dto.setName(request.getName());
    dto.setEmail(request.getEmail());
    UserDTO createdUser = userService.createUser(dto, request.getPassword());
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }
}
