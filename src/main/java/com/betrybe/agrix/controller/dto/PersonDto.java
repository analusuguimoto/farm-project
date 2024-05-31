package com.betrybe.agrix.controller.dto;


import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.security.Role;

/**
 * Person Create Dto.
 */
public record PersonDto(Long id, String username, Role role) {
  /**
   * Methods to Entity.
   */
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(person.getId(), person.getUsername(), person.getRole());
  }

}
