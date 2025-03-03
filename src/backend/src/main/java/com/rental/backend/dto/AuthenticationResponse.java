package com.rental.backend.dto;

public class AuthenticationResponse {
  private String token;

  public AuthenticationResponse() {}
  public AuthenticationResponse(String token) {
    this.token = token;
  }

  // getters, setters

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}

