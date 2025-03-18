/* package com.rental.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
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
    return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
    String errorMessage = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
    return buildResponse(HttpStatus.CONFLICT, "Erreur de base de données : " + errorMessage);
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
    return buildResponse(HttpStatus.UNAUTHORIZED, "Authentification requise.");
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<Map<String, Object>> handleAccessDeniedException(AccessDeniedException ex) {
    return buildResponse(HttpStatus.FORBIDDEN, "Accès refusé.");
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Map<String, Object>> handleArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
    return buildResponse(HttpStatus.BAD_REQUEST, "Type de paramètre incorrect.");
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<Map<String, Object>> handleMissingParameter(MissingServletRequestParameterException ex) {
    return buildResponse(HttpStatus.BAD_REQUEST, "Paramètre manquant : " + ex.getParameterName());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<Map<String, Object>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
    return buildResponse(HttpStatus.METHOD_NOT_ALLOWED, "Méthode HTTP non supportée.");
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<Map<String, Object>> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
    return buildResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Type de média non supporté.");
  }

  @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
  public ResponseEntity<Map<String, Object>> handleMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
    return buildResponse(HttpStatus.NOT_ACCEPTABLE, "Type de média non acceptable.");
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<Map<String, Object>> handleNoHandlerFound(NoHandlerFoundException ex, HttpServletRequest request) {
    if (isSwaggerRequest(request.getRequestURI())) {
      return ResponseEntity.notFound().build();
    }
    return buildResponse(HttpStatus.NOT_FOUND, "Route non trouvée.");
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
    return buildResponse(HttpStatus.BAD_REQUEST, "Corps de la requête mal formé.");
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException ex) {
    return buildResponse(HttpStatus.BAD_REQUEST, "Violation de contrainte.");
  }

  @ExceptionHandler(DuplicateResourceException.class)
  public ResponseEntity<Map<String, Object>> handleDuplicateResource(DuplicateResourceException ex) {
    return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
    return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex, HttpServletRequest request) {
    if (isSwaggerRequest(request.getRequestURI())) {
      return ResponseEntity.ok(new HashMap<>());
    }
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur serveur.");
  }

  private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", status.value());
    response.put("message", message);
    return ResponseEntity.status(status).body(response);
  }

  private boolean isSwaggerRequest(String requestURI) {
    return requestURI.contains("/v3/api-docs") ||
      requestURI.contains("/swagger-ui") ||
      requestURI.contains("/swagger-resources") ||
      requestURI.contains("/swagger-ui.html");
  }
} */
