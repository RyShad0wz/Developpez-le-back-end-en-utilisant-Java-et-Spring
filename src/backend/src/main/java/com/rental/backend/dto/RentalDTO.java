package com.rental.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


import java.time.LocalDateTime;

@Schema(description = "DTO pour les locations")
public class RentalDTO {

  @Schema(description = "Identifiant unique", example = "1")
  private Long id;

  @NotBlank(message = "Le nom de la location est obligatoire")
  @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
  @Schema(description = "Nom de la location", example = "Appartement T2", required = true)
  private String name;

  @NotBlank(message = "La description est obligatoire")
  @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères")
  @Schema(description = "Description de la location", example = "Appartement de 50m2", required = true)
  private String description;

  @NotNull(message = "Le prix est obligatoire")
  @Positive(message = "Le prix doit être un nombre positif")
  @Schema(description = "Prix de la location", example = "500.0", required = true)
  private Double price;

  @NotNull(message = "La surface est obligatoire")
  @Positive(message = "La surface doit être un nombre positif")
  @Schema(description = "Surface de la location", example = "50.0", required = true)
  private Double surface;

  @NotBlank(message = "L'URL de l'image est obligatoire")
  @Schema(description = "URL de l'image de la location", example = "https://example.com/image.jpg", required = true)
  private String picture;

  @NotNull(message = "L'identifiant du propriétaire est obligatoire")
  @Schema(description = "Identifiant du propriétaire", example = "1", required = true)
  private Long ownerId;

  @Schema(description = "Date de création de la location", example = "2023-10-01T12:00:00")
  private LocalDateTime createdAt;

  @Schema(description = "Date de mise à jour de la location", example = "2023-10-01T12:00:00")
  private LocalDateTime updatedAt;

  public RentalDTO() {}

  public RentalDTO(Long id, String name, String description, Double price, Double surface, String picture, Long ownerId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.surface = surface;
    this.picture = picture;
    this.ownerId = ownerId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
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
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public Double getPrice() {
    return price;
  }
  public void setPrice(Double price) {
    this.price = price;
  }
  public Double getSurface() {
    return surface;
  }
  public void setSurface(Double surface) {
    this.surface = surface;
  }
  public String getPicture() {
    return picture;
  }
  public void setPicture(String picture) {
    this.picture = picture;
  }
  public Long getOwnerId() {
    return ownerId;
  }
  public void setOwnerId(Long ownerId) {
    this.ownerId = ownerId;
  }
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
