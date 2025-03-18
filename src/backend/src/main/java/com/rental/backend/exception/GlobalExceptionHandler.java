package com.rental.backend.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("message", ex.getMessage());
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.CONFLICT.value());

    String errorMessage = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
    if (errorMessage.contains("unique constraint")) {
      response.put("message", "Violation de contrainte d'unicité : " + errorMessage);
    } else if (errorMessage.contains("foreign key constraint")) {
      response.put("message", "Violation de clé étrangère : " + errorMessage);
    } else {
      response.put("message", "Violation de contrainte d'intégrité : " + errorMessage);
    }

    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, Object> response = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
      response.put(error.getField(), error.getDefaultMessage()));
    response.put("status", HttpStatus.BAD_REQUEST.value());
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<Map<String, Object>> handleAuthenticationException(AuthenticationException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.UNAUTHORIZED.value());
    response.put("message", "Authentification requise : " + ex.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<Map<String, Object>> handleAccessDeniedException(AccessDeniedException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.FORBIDDEN.value());
    response.put("message", "Accès refusé : " + ex.getMessage());
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Map<String, Object>> handleArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("message", "Type de paramètre incorrect : " + ex.getName() + " doit être de type " + ex.getRequiredType().getSimpleName());
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<Map<String, Object>> handleMissingParameter(MissingServletRequestParameterException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("message", "Paramètre manquant : " + ex.getParameterName());
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<Map<String, Object>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.METHOD_NOT_ALLOWED.value());
    response.put("message", "Méthode HTTP non supportée : " + ex.getMethod());
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<Map<String, Object>> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    response.put("message", "Type de média non supporté : " + ex.getContentType());
    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(response);
  }

  @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
  public ResponseEntity<Map<String, Object>> handleMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.NOT_ACCEPTABLE.value());
    response.put("message", "Type de média non acceptable : " + ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<Map<String, Object>> handleNoHandlerFound(NoHandlerFoundException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.NOT_FOUND.value());
    response.put("message", "Route non trouvée : " + ex.getRequestURL());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("message", "Corps de la requête mal formé : " + ex.getMessage());
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("message", "Violation de contrainte : " + ex.getMessage());
    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(DuplicateResourceException.class)
  public ResponseEntity<Map<String, Object>> handleDuplicateResource(DuplicateResourceException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.CONFLICT.value());
    response.put("message", ex.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.NOT_FOUND.value());
    response.put("message", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.put("message", "Une erreur s'est produite");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
