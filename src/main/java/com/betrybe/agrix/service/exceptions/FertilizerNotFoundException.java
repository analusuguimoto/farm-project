package com.betrybe.agrix.service.exceptions;

/**
 * Exception created for Fertilizer not found.
 */
public class FertilizerNotFoundException extends NotFoundException {
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }
}
