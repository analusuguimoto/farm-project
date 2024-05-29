package com.betrybe.agrix.service.exceptions;

/**
 * Exception created for Farms not found.
 */
public class FarmNotFoundException extends NotFoundException {
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
