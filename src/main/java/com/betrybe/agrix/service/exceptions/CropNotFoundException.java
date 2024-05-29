package com.betrybe.agrix.service.exceptions;

/**
 * Exception created for Crops not found.
 */
public class CropNotFoundException extends NotFoundException {
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}
