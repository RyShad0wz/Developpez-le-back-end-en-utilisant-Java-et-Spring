package com.rental.backend.controller;

import com.rental.backend.dto.MessageDTO;
import com.rental.backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

  private final MessageService messageService;

  @Autowired
  public MessageController(MessageService messageService){
    this.messageService = messageService;
  }

  @GetMapping("/rental/{rentalId}")
  public ResponseEntity<List<MessageDTO>> getMessagesByRental(@PathVariable Long rentalId){
    List<MessageDTO> messages = messageService.getMessagesByRentalId(rentalId);
    return ResponseEntity.ok(messages);
  }

  @PostMapping
  public ResponseEntity<MessageDTO> sendMessage(@RequestBody MessageDTO messageDTO){
    MessageDTO createdMessage = messageService.sendMessage(messageDTO);
    return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
  }
}

