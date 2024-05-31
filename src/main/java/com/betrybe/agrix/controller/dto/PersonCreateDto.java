package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.security.Role;


/**
 * Person Create Dto.
 */
public record PersonCreateDto(String username, String password, Role role) {
  /**
   * Methods to Entity.
   */
  public Person toEntity() {
    return new Person(username, password, role);
  }
}
