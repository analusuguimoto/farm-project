package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Farm;

/**
 * Farm Create Dto.
 */
public record FarmCreateDto(String name, Double size) {

  /**
   * Methods to Entity.
   */
  public Farm toEntity() {
    return new Farm(name, size);
  }
}
