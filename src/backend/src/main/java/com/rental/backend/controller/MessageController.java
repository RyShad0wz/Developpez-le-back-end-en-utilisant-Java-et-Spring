package com.rental.backend.controller;

import com.rental.backend.dto.MessageDTO;
import com.rental.backend.service.MessageService;
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


import java.util.List;

@RestController
@RequestMapping("/api/messages")
@Tag(name = "Messages", description = "Endpoints pour la gestion des messages")
public class MessageController {

  private final MessageService messageService;

  @Autowired
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/rental/{rental_id}")
  @Operation(summary = "Récupérer les messages d'une location", description = "Récupère tous les messages associés à une location spécifique")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Messages récupérés avec succès",
      content = @Content(schema = @Schema(implementation = MessageDTO.class, type = "array"))),
    @ApiResponse(responseCode = "404", description = "Location non trouvée"),
    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
  })
  public ResponseEntity<List<MessageDTO>> getMessagesByRental(
    @Parameter(description = "ID de la location", example = "1", required = true)
    @PathVariable Long rental_id
  ) {
    List<MessageDTO> messages = messageService.getMessagesByRentalId(rental_id);
    return ResponseEntity.ok(messages);
  }

  @PostMapping
  @Operation(summary = "Envoyer un message", description = "Envoie un nouveau message")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Message envoyé avec succès",
      content = @Content(schema = @Schema(implementation = MessageDTO.class))),
    @ApiResponse(responseCode = "400", description = "Données du message invalides"),
    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
  })
  public ResponseEntity<MessageDTO> sendMessage(
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Données du message à envoyer",
      required = true,
      content = @Content(schema = @Schema(implementation = MessageDTO.class)))
    @Valid @RequestBody MessageDTO messageDTO
  ) {
    MessageDTO sentMessage = messageService.sendMessage(messageDTO);
    return ResponseEntity.ok(sentMessage);
  }
}
