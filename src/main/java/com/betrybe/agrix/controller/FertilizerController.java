package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerCreateDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import com.betrybe.agrix.service.exceptions.FertilizerNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * Fertilizer Controller with its methods.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {
  private final FertilizerService service;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.service = fertilizerService;
  }

  /**
   * Post route for Fertilizer Creation.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FertilizerDto createFertilizer(@RequestBody FertilizerCreateDto fertilizerCreateDto) {
    return FertilizerDto.fromEntity(
      service.createFertilizer(fertilizerCreateDto.toEntity())
    );
  }

  /**
   * Get route for getting all Fertilizers .
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public List<FertilizerDto> getAllFertilizers() {
    List<Fertilizer> fertilizers = service.getAllFertilizers();
    List<FertilizerDto> fertilizersList = fertilizers.stream()
        .map(FertilizerDto::fromEntity).toList();

    return fertilizersList;
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public FertilizerDto geFertilizerByFertilizerId(@PathVariable Long id)
      throws FertilizerNotFoundException {
    return FertilizerDto.fromEntity(service.getFertilizerById(id));
  }

}
