package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Fertilizer;

/**
 * Implementing Fertilize Dto.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {
  /**
   * Methods from Entity.
   */

  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
      fertilizer.getId(),
      fertilizer.getName(),
      fertilizer.getBrand(),
      fertilizer.getComposition()
      );
  }
}
