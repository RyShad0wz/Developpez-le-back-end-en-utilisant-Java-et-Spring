package com.rental.backend.exception;

public class ResourceNotFoundException extends RuntimeException {

  /**
   * Constructeur avec un message d'erreur personnalisé.
   *
   * @param message Le message d'erreur décrivant la ressource non trouvée.
   */
  public ResourceNotFoundException(String message) {
    super(message);
  }

  /**
   * Constructeur avec un message d'erreur et une cause.
   *
   * @param message Le message d'erreur décrivant la ressource non trouvée.
   * @param cause   La cause de l'exception (une autre exception).
   */
  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
