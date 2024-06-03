package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropsDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.CropsService;
import com.betrybe.agrix.service.exceptions.CropNotFoundException;
import com.betrybe.agrix.service.exceptions.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crops Controller with its methods.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropsController {
  private final CropsService cropsService;

  @Autowired
  public CropsController(CropsService cropsService) {
    this.cropsService = cropsService;
  }

  /**
   * Get route for getting a List of Crops.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MANAGER')")
  public List<CropsDto> getAllCrops() {
    List<Crop> crops = cropsService.getAllCrops();
    List<CropsDto> cropsData = crops.stream().map(CropsDto::fromEntity).toList();

    return cropsData;
  }

  @GetMapping("/{id}")
  public CropsDto getCropById(@PathVariable Long id) throws CropNotFoundException {
    Crop crop = cropsService.getCropById(id);
    return CropsDto.fromEntity(crop);
  }

  /**
   * Get route for getting a List of Crops filtered by harvest date.
   */
  @GetMapping("/search")
  public List<CropsDto> getCropByHarvestDate(
      @RequestParam LocalDate start,
      @RequestParam LocalDate end
  ) {
    List<Crop> crops = cropsService.findCropsByHarvestDate(start, end);
    List<CropsDto> cropsData = crops.stream().map(CropsDto::fromEntity).toList();

    return cropsData;
  }

  /**
   * Implementation of Post Route to associate crops and fertilizers.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public String associateCropsFertilizers(@PathVariable Long cropId,
      @PathVariable Long fertilizerId) throws CropNotFoundException, FertilizerNotFoundException {
    cropsService.associateCropFertilizer(cropId, fertilizerId);

    return "Fertilizante e plantação associados com sucesso!";
  }

  /**
   * Implementation of Get Route to get all fertilizers inside a crop.
   */
  @GetMapping("/{cropId}/fertilizers")
  @ResponseStatus(HttpStatus.OK)
  public List<FertilizerDto> getFertilizersByCropId(@PathVariable Long cropId)
      throws CropNotFoundException {
    List<Fertilizer> fertilizers = cropsService.getFertilizersByCropId(cropId);
    List<FertilizerDto> fertilizerData = fertilizers
        .stream().map(FertilizerDto::fromEntity).toList();

    return fertilizerData;

  }

}
