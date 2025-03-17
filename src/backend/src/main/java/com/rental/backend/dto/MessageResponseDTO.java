package com.rental.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Réponse de l'API après l'envoi d'un message")
public class MessageResponseDTO {

  @Schema(description = "Message de succès", example = "Message send with success")
  private String message;

  public MessageResponseDTO() {}

  public MessageResponseDTO(String message) {
    this.message = message;
  }

  // Getters et Setters

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
