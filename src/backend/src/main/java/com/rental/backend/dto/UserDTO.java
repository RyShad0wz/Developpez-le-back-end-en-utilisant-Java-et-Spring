package com.rental.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Objet de transfert pour les utilisateurs")
public class UserDTO {

  @Schema(description = "Identifiant de l'utilisateur", example = "1")
  private Long id;

  @Schema(description = "Nom de l'utilisateur", example = "John Doe")
  private String name;

  @Schema(description = "Adresse email de l'utilisateur", example = "john@example.com")
  private String email;

  @Schema(description = "Date de création de l'utilisateur", example = "2021-06-01T12:00:00")
  private LocalDateTime created_at;

  @Schema(description = "Date de mise à jour de l'utilisateur", example = "2021-06-01T12:00:00")
  private LocalDateTime updated_at;

  public UserDTO() {}

  public UserDTO(Long id, String name, String email, LocalDateTime created_at, LocalDateTime updated_at) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.created_at = created_at;
    this.updated_at = updated_at;
  }

  // Getters et Setters

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public LocalDateTime getCreatedAt() {
    return created_at;
  }
  public void setCreatedAt(LocalDateTime created_at) {
    this.created_at = created_at;
  }
  public LocalDateTime getUpdatedAt() {
    return updated_at;
  }
  public void setUpdatedAt(LocalDateTime updated_at) {
    this.updated_at = updated_at;
  }
}
