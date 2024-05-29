package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Fertilizer;

/**
 * Fertilizer Create Dto.
 */
public record FertilizerCreateDto(String name, String brand, String composition) {

  /**
   * Methods to Entity.
   */
  public Fertilizer toEntity() {
    return new Fertilizer(name, brand, composition);
  }
}
