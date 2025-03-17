package com.rental.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "DTO pour les locations")
public class RentalDTO {

  @Schema(description = "Identifiant unique", example = "1")
  private Long id;

  @Schema(description = "Nom de la location", example = "Appartement T2")
  private String name;

  @Schema(description = "Description de la location", example = "Appartement de 50m2")
  private String description;

  @Schema(description = "Prix de la location", example = "500")
  private Double price;

  @Schema(description = "Surface de la location", example = "50")
  private Double surface;

  @Schema(description = "URL de l'image de la location", example = "https://example.com/image.jpg")
  private String picture;

  @Schema(description = "Identifiant du propriétaire", example = "1")
  private Long owner_id;

  @Schema(description = "Date de création de la location")
  private LocalDateTime created_at;

  @Schema(description = "Date de mise à jour de la location")
  private LocalDateTime updated_at;

  public RentalDTO() {}

  public RentalDTO(Long id, String name, String description, Double price, Double surface, String picture, Long owner_id, LocalDateTime created_at, LocalDateTime updated_at) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.surface = surface;
    this.picture = picture;
    this.owner_id = owner_id;
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
    return owner_id;
  }
  public void setOwnerId(Long owner_id) {
    this.owner_id = owner_id;
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

