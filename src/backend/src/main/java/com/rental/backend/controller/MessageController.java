package com.rental.backend.controller;

import com.rental.backend.dto.MessageDTO;
import com.rental.backend.service.MessageService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@Schema(description = "Endpoints pour les messages")
public class MessageController {

  @Schema(description = "Récupérer les messages d'une location")
  private final MessageService messageService;

  @Autowired
  public MessageController(MessageService messageService){
    this.messageService = messageService;
  }

  @Schema(description = "Récupérer les messages d'une location")
  @GetMapping("/rental/{rentalId}")
  public ResponseEntity<List<MessageDTO>> getMessagesByRental(@PathVariable Long rentalId){
    List<MessageDTO> messages = messageService.getMessagesByRentalId(rentalId);
    return ResponseEntity.ok(messages);
  }

  @Schema(description = "Envoyer un message")
  @PostMapping
  public MessageDTO sendMessage(@RequestBody MessageDTO messageDTO) {
    return messageService.sendMessage(messageDTO);
  }

}

