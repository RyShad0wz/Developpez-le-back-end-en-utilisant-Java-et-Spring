package com.rental.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "DTO pour les locations")
public class RentalDTO {

  @Schema(description = "Identifiant unique", example = "1")
  private Long id;

  @Schema(description = "Nom de la location", example = "New Home")
  private String name;

  @Schema(description = "Description de la location", example = "New Home for Rent")
  private String description;

  @Schema(description = "Prix de la location", example = "500")
  private Double price;

  @Schema(description = "Surface de la location", example = "50")
  private Double surface;

  @Schema(description = "URL de l'image de la location", example = "https://www.demeures-cote-dargent.com/wp-content/uploads/2023/07/Groupe_InCA-M8_010.jpg")
  private String picture;

  @Schema(description = "Identifiant du propriétaire", example = "1")
  @JsonProperty("owner_id")
  private Long ownerId;

  @Schema(description = "Date de création de la location")
  @JsonFormat(pattern = "yyyy/MM/dd")
  @JsonProperty("created_at")
  private LocalDateTime createdAt;

  @Schema(description = "Date de mise à jour de la location")
  @JsonFormat(pattern = "yyyy/MM/dd")
  @JsonProperty("updated_at")
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

