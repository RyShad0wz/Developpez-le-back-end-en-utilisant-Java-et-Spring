package com.rental.backend.controller;

import com.rental.backend.dto.RentalDTO;
import com.rental.backend.service.CloudinaryService;
import com.rental.backend.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
@Tag(name = "Rentals", description = "Endpoints pour la gestion des locations")
public class RentalController {

  @Autowired
  private RentalService rentalService;

  @Autowired
  private CloudinaryService cloudinaryService;

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

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(summary = "Créer une nouvelle location", description = "Ajoute une nouvelle location à la base de données")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Location créée avec succès",
      content = @Content(schema = @Schema(implementation = Map.class))),
    @ApiResponse(responseCode = "400", description = "Données de la location invalides"),
    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
  })
  @io.swagger.v3.oas.annotations.parameters.RequestBody(
    description = "Données de la location à créer",
    required = true,
    content = @Content(
      mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
      schema = @Schema(implementation = RentalDTO.class),
      examples = @ExampleObject(
        value = """
                    {
                      "name": "New Home",
                      "surface": 50.0,
                      "price": 500.0,
                      "description": "New Home for Rent",
                      "picture": "Fichier image (JPEG, PNG)"
                    }
                    """
      )
    )
  )
  public ResponseEntity<Map<String, Object>> createRental(
    @RequestParam("name") @NotBlank String name,
    @RequestParam("surface") @NotNull @Positive Double surface,
    @RequestParam("price") @NotNull @Positive Double price,
    @RequestParam("description") @NotBlank String description,
    @RequestParam("picture") @NotNull MultipartFile picture
  ) throws IOException {
    // Uploader l'image vers Cloudinary
    String pictureUrl = cloudinaryService.uploadImage(picture);

    // Créer le DTO avec l'URL de l'image
    RentalDTO rentalDTO = new RentalDTO();
    rentalDTO.setName(name);
    rentalDTO.setSurface(surface);
    rentalDTO.setPrice(price);
    rentalDTO.setDescription(description);
    rentalDTO.setPicture(pictureUrl);

    // Enregistrer la location dans la base de données
    RentalDTO createdRental = rentalService.createRental(rentalDTO);

    // Retourner la réponse
    Map<String, Object> response = new HashMap<>();
    response.put("message", "Rental created !");
    response.put("rental", createdRental);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(summary = "Mettre à jour une location", description = "Met à jour les informations d'une location existante")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Location mise à jour avec succès",
      content = @Content(schema = @Schema(implementation = Map.class))),
    @ApiResponse(responseCode = "400", description = "Données de la location invalides"),
    @ApiResponse(responseCode = "404", description = "Location non trouvée"),
    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
  })
  @io.swagger.v3.oas.annotations.parameters.RequestBody(
    description = "Données de la location à mettre à jour",
    required = true,
    content = @Content(
      mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
      schema = @Schema(implementation = RentalDTO.class),
      examples = @ExampleObject(
        value = """
                    {
                      "name": "Updated Home",
                      "surface": 60.0,
                      "price": 600.0,
                      "description": "Updated Home for Rent"
                    }
                    """
      )
    )
  )
  public ResponseEntity<Map<String, Object>> updateRental(
    @Parameter(description = "ID de la location à mettre à jour", example = "1", required = true)
    @PathVariable Long id,
    @RequestParam("name") @NotBlank String name,
    @RequestParam("surface") @NotNull @Positive Double surface,
    @RequestParam("price") @NotNull @Positive Double price,
    @RequestParam("description") @NotBlank String description
  ) {
    // Créer le DTO
    RentalDTO rentalDTO = new RentalDTO();
    rentalDTO.setName(name);
    rentalDTO.setSurface(surface);
    rentalDTO.setPrice(price);
    rentalDTO.setDescription(description);

    // Mettre à jour la location dans la base de données
    RentalDTO updatedRental = rentalService.update(id, rentalDTO);

    // Retourner la réponse
    Map<String, Object> response = new HashMap<>();
    response.put("message", "Rental updated !");
    response.put("rental", updatedRental);
    return ResponseEntity.ok(response);
  }
}
