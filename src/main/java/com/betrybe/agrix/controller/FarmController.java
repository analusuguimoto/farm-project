package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropsCreateDto;
import com.betrybe.agrix.controller.dto.CropsDto;
import com.betrybe.agrix.controller.dto.FarmCreateDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.CropsService;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exceptions.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * Farm Controller with its methods.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {
  private final FarmService farmService;
  private final CropsService cropsService;

  @Autowired
  public FarmController(FarmService farmService, CropsService cropsService) {
    this.farmService = farmService;
    this.cropsService = cropsService;
  }

  /**
   * Post route for Farm Creation.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto createFarm(@RequestBody FarmCreateDto farmCreateDto) {
    return FarmDto.fromEntity(
      farmService.createFarm(farmCreateDto.toEntity())
    );
  }

  /**
   * Get route for getting a List of Farms.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<FarmDto> getAllFarms() {
    List<Farm> farms = farmService.getAllFarms();

    return farms.stream().map(FarmDto::fromEntity).toList();
  }

  @GetMapping("/{id}")
  public FarmDto getFarmById(@PathVariable Long id) throws FarmNotFoundException {
    return FarmDto.fromEntity(farmService.getFarmById(id));
  }

  /**
   * Post route for creating crops.
   */
  @PostMapping("/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropsDto createCrops(@PathVariable Long farmId, @RequestBody CropsCreateDto cropsCreateDto)
      throws FarmNotFoundException {
    return CropsDto.fromEntity(
            cropsService.createCrops(farmId, cropsCreateDto.toEntity())
    );
  }

  /**
   * Get route for getting crops by farm's id.
   */
  @GetMapping("/{farmId}/crops")
  @ResponseStatus(HttpStatus.OK)
  public List<CropsDto> getCropByFarmId(@PathVariable Long farmId) throws FarmNotFoundException {
    Farm farm = farmService.getFarmById(farmId);

    return farm.getCrops().stream().map(CropsDto::fromEntity).toList();
  }
}
