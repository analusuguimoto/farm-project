package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Farm;

/**
 * Farm Dto.
 */
public record FarmDto(Long id, String name, Double size) {
  /**
   * Methods from Entity.
   */
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(
        farm.getId(),
        farm.getName(),
        farm.getSize()
        );
  }
}
