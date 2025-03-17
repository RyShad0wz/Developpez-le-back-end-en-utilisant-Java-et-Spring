package com.rental.backend.controller;

import com.rental.backend.dto.MessageDTO;
import com.rental.backend.dto.MessageResponseDTO;
import com.rental.backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/messages")
public class MessageController {

  private final MessageService messageService;

  @Autowired
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @PostMapping
  public ResponseEntity<MessageResponseDTO> sendMessage(@RequestBody MessageDTO messageDTO) {
    MessageResponseDTO response = messageService.sendMessage(messageDTO);
    return ResponseEntity.ok(response);
  }
}
