package com.betrybe.agrix.service.exceptions;

/**
 * General Not Found Exception created extending from Exception.
 */
public class NotFoundException extends Exception {
  public NotFoundException(String message) {
    super(message);
  }
}
