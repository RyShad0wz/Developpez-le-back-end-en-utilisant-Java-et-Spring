package com.rental.backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MessageDTO {
  private Long id;
  private Long rentalId;
  private Long userId;
  private String message;
  private LocalDate createdAt;
  private LocalDate updatedAt;

  public MessageDTO() {}

  public MessageDTO(Long id, Long rentalId, Long userId, String message, LocalDate createdAt, LocalDate updatedAt) {
    this.id = id;
    this.rentalId = rentalId;
    this.userId = userId;
    this.message = message;
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
  public LocalDate getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }
  public LocalDate getUpdatedAt() {
    return updatedAt;
  }
  public void setUpdatedAt(LocalDate updatedAt) {
    this.updatedAt = updatedAt;
  }
}

