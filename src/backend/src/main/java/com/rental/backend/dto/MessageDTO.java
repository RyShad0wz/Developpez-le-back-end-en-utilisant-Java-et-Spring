package com.rental.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Objet de transfert pour les messages")
public class MessageDTO {

  @Schema(description = "Identifiant du message", example = "1")
  private Long id;

  @Schema(description = "Identifiant de la location", example = "1")
  private Long rentalId;

  @Schema(description = "Identifiant de l'utilisateur", example = "1")
  private Long userId;

  @Schema(description = "Contenu du message", example = "Super séjour, je recommande !")
  private String message;

  public MessageDTO() {}

  public MessageDTO(Long id, Long rentalId, Long userId, String message) {
    this.id = id;
    this.rentalId = rentalId;
    this.userId = userId;
    this.message = message;
  }

  // Getters et Setters

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Long getRentalId() {
    return rentalId;
  }
  public void setRentalId(Long rentalId) {
    this.rentalId = rentalId;
  }
  public Long getUserId() {
    return userId;
  }
  public void setUserId(Long userId) {
    this.userId = userId;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }

}

