package com.rental.backend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "RENTALS")
public class Rental {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Identifiant unique", example = "1")
  private Long id;

  @Schema(description = "Nom de la maison à louer", example = "Appartement cosy")
  private String name;

  @Schema(description = "Surface en m²", example = "45.0")
  private Double surface;

  @Schema(description = "Prix de la location par nuit", example = "50.0")
  private Double price;

  @Schema(description = "URL de l'image", example = "https://www.example.com/picture.jpg")
  private String picture;

  @Schema(description = "Description de la location", example = "Appartement cosy avec vue sur la mer")
  private String description;

  // Relation vers l'utilisateur propriétaire
  @Schema(description = "Propriétaire de la location")
  @ManyToOne
  @JoinColumn(name = "owner_id", nullable = false)
  private User owner;

  @Schema(description = "Date de création de la location")
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Schema(description = "Date de mise à jour de la location")
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  // Constructeurs, getters, setters...

  public Rental() {
  }

  public Rental(Long id, String name, Double surface, Double price, String picture, String description, User owner, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.name = name;
    this.surface = surface;
    this.price = price;
    this.picture = picture;
    this.description = description;
    this.owner = owner;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  // ... getters & setters ...

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

  public Double getSurface() {
    return surface;
  }

  public void setSurface(Double surface) {
    this.surface = surface;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
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

