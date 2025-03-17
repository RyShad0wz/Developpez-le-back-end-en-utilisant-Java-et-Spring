package com.rental.backend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "USER")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Identifiant unique", example = "1")
  private Long id;

  @Schema(description = "Adresse email", example = "john@example.com")
  private String email;

  @Schema(description = "Nom de l'utilisateur", example = "John Doe")
  private String name;

  @Schema(description = "Mot de passe", example = "secret")
  private String password;

  @Schema(description = "Date de création de l'utilisateur")
  @Column(name = "created_at")
  private LocalDateTime created_at;

  @Schema(description = "Date de mise à jour de l'utilisateur")
  @Column(name = "updated_at")
  private LocalDateTime updated_at;

  public User() {

  }

  public User(Long id, String email, String name, String password, LocalDateTime created_at, LocalDateTime updated_at) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.password = password;
    this.created_at = created_at;
    this.updated_at = updated_at;
  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
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

