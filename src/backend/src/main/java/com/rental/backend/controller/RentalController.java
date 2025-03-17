package com.rental.backend.controller;

import com.rental.backend.dto.RentalDTO;
import com.rental.backend.service.RentalService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
@Tag(name = "Rentals", description = "Endpoints pour la gestion des locations")
public class RentalController {

  private final RentalService rentalService;

  @Autowired
  public RentalController(RentalService rentalService) {
    this.rentalService = rentalService;
  }

  @GetMapping
  @Operation(summary = "Obtenir toutes les locations", description = "Récupère la liste de toutes les locations disponibles")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Liste des locations récupérée avec succès",
      content = @Content(schema = @Schema(implementation = RentalDTO.class, type = "array"))),
    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
  })
  public ResponseEntity<Map<String, List<RentalDTO>>> getAllRentals() {
    List<RentalDTO> rentals = rentalService.getAllRentals();
    Map<String, List<RentalDTO>> response = new HashMap<>();
    response.put("rentals", rentals);

    return ResponseEntity.ok(response);
  }


  @GetMapping("/{id}")
  @Operation(summary = "Obtenir une location par son ID", description = "Récupère les détails d'une location spécifique")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Location trouvée",
      content = @Content(schema = @Schema(implementation = RentalDTO.class))),
    @ApiResponse(responseCode = "404", description = "Location non trouvée"),
    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
  })
  public ResponseEntity<RentalDTO> getRentalById(
    @Parameter(description = "ID de la location", example = "1", required = true)
    @PathVariable Long id
  ) {
    RentalDTO rental = rentalService.getRentalById(id);
    return ResponseEntity.ok(rental);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Créer une nouvelle location", description = "Ajoute une nouvelle location à la base de données")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Location créée avec succès",
      content = @Content(schema = @Schema(implementation = RentalDTO.class))),
    @ApiResponse(responseCode = "400", description = "Données de la location invalides"),
    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
  })
  public ResponseEntity<RentalDTO> createRental(
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Données de la location à créer",
      required = true,
      content = @Content(schema = @Schema(implementation = RentalDTO.class)))
    @Valid @RequestBody RentalDTO rentalDTO
  ) {
    RentalDTO createdRental = rentalService.createRental(rentalDTO);
    return new ResponseEntity<>(createdRental, HttpStatus.CREATED);
  }

  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Mettre à jour une location", description = "Met à jour les informations d'une location existante")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Location mise à jour avec succès",
      content = @Content(schema = @Schema(implementation = RentalDTO.class))),
    @ApiResponse(responseCode = "400", description = "Données de la location invalides"),
    @ApiResponse(responseCode = "404", description = "Location non trouvée"),
    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
  })
  public ResponseEntity<RentalDTO> updateRental(
    @Parameter(description = "ID de la location à mettre à jour", example = "1", required = true)
    @PathVariable Long id,
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Nouvelles données de la location",
      required = true,
      content = @Content(schema = @Schema(implementation = RentalDTO.class)))
    @Valid @RequestBody RentalDTO rentalDTO
  ) {
    RentalDTO updatedRental = rentalService.update(id, rentalDTO);
    return ResponseEntity.ok(updatedRental);
  }
}
