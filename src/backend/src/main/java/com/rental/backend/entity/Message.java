package com.rental.backend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "MESSAGES")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Identifiant unique", example = "1")
  private Long id;

  // Relation vers la location
  @ManyToOne
  @JoinColumn(name = "rental_id", nullable = false)
  @Schema(description = "Location associée au message")
  private Rental rental;

  // Relation vers l'utilisateur
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @Schema(description = "Utilisateur ayant posté le message")
  private User user;

  @Schema(description = "Contenu du message", example = "Super séjour, je recommande !")
  private String message;

  // Constructeurs, getters, setters

  public Message() {
  }

public Message(Long id, Rental rental, User user, String message) {
    this.id = id;
    this.rental = rental;
    this.user = user;
    this.message = message;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Rental getRental() {
    return rental;
  }

  public void setRental(Rental rental) {
    this.rental = rental;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
