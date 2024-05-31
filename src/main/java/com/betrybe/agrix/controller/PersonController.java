package com.betrybe.agrix.controller;


import com.betrybe.agrix.controller.dto.PersonCreateDto;
import com.betrybe.agrix.controller.dto.PersonDto;
import com.betrybe.agrix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Persons Controller with its methods.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
  private final PersonService service;

  @Autowired
  public PersonController(PersonService service) {
    this.service = service;
  }

  /**
   * Post route for Person Creation.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto createNewPerson(@RequestBody PersonCreateDto personCreateDto) {
    return PersonDto.fromEntity(service.create(personCreateDto.toEntity()));
  }
}
