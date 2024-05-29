package com.betrybe.agrix.advice;

import com.betrybe.agrix.service.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global Controller Advice implemented.
 */
@ControllerAdvice
public class GlobalControllerAdvice {
  @ExceptionHandler
  public ResponseEntity<String> handleNotFound(NotFoundException exc) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
  }
}
