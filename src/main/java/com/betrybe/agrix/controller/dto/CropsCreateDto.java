package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Crop;
import java.time.LocalDate;

/**
 * Crops Create Dto.
 */
public record CropsCreateDto(String name, Double plantedArea,
    LocalDate plantedDate, LocalDate harvestDate) {
  /**
   * Methods to Entity.
   */
  public Crop toEntity() {
    return new Crop(name, plantedArea, plantedDate, harvestDate);
  }
}
