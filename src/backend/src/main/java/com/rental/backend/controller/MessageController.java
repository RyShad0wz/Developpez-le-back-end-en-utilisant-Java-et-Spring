package com.rental.backend.controller;

import com.rental.backend.dto.MessageDTO;
import com.rental.backend.dto.MessageResponseDTO;
import com.rental.backend.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@Tag(name = "Messages", description = "Endpoints pour la gestion des messages")
public class MessageController {

  private final MessageService messageService;

  @Autowired
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @PostMapping
  @Operation(summary = "Envoyer un message", description = "Envoie un nouveau message")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Message envoyé avec succès",
      content = @Content(schema = @Schema(implementation = MessageResponseDTO.class))),
    @ApiResponse(responseCode = "400", description = "Données du message invalides"),
    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
  })
  public ResponseEntity<MessageResponseDTO> sendMessage(
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Données du message à envoyer",
      required = true,
      content = @Content(schema = @Schema(implementation = MessageDTO.class))
    )
    @RequestBody MessageDTO messageDTO
  ) {
    MessageResponseDTO response = messageService.sendMessage(messageDTO);
    return ResponseEntity.ok(response);
  }
}
