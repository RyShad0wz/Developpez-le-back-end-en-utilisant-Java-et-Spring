package com.rental.backend.dto;

public class RentalResponseDTO {
  private String message;

  public RentalResponseDTO(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
