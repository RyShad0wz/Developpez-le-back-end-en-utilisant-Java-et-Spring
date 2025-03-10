package com.rental.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objet de transfert pour la connexion")
public class LoginRequest {

  @Schema(description = "Adresse email de l'utilisateur")
  private String email;

  @Schema(description = "Mot de passe de l'utilisateur")
  private String password;

  // getters, setters

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

