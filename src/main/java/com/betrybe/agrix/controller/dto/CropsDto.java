package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Crop;
import java.time.LocalDate;

/**
 * Implementing Crop Dto.
 */
public record CropsDto(Long id, String name, Double plantedArea,
    Long farmId, LocalDate plantedDate, LocalDate harvestDate) {
  /**
   * Methods from Entity.
   */

  public static CropsDto fromEntity(Crop crop) {
    return new CropsDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId(),
        crop.getPlantedDate(),
        crop.getHarvestDate()
      );
  }
}
