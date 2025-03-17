package com.rental.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Objet de transfert pour les messages")
public class MessageDTO {

  @Schema(description = "Identifiant du message", example = "1")
  private Long id;

  @Schema(description = "Identifiant de la location", example = "1")
  private Long rental_id;

  @Schema(description = "Identifiant de l'utilisateur", example = "1")
  private Long user_id;

  @Schema(description = "Contenu du message", example = "Super séjour, je recommande !")
  private String message;

  public MessageDTO() {}

  public MessageDTO(Long id, Long rental_id, Long user_id, String message) {
    this.id = id;
    this.rental_id = rental_id;
    this.user_id = user_id;
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
    return rental_id;
  }
  public void setRentalId(Long rental_id) {
    this.rental_id = rental_id;
  }
  public Long getUserId() {
    return user_id;
  }
  public void setUserId(Long user_id) {
    this.user_id = user_id;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }

}

