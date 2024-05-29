package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.service.exceptions.CropNotFoundException;
import com.betrybe.agrix.service.exceptions.FarmNotFoundException;
import com.betrybe.agrix.service.exceptions.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Crops Service implementation.
 */
@Service
public class CropsService {
  private final CropRepository cropRepository;
  private final FarmService farmService;
  private final FertilizerService fertilizerService;

  /**
   * Constructor.
   */
  @Autowired
  public CropsService(CropRepository cropRepository,
      FarmService farmService, FertilizerService fertilizerService) {
    this.cropRepository = cropRepository;
    this.farmService = farmService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Implementation of CropsService and CreateCrops method.
   */
  public Crop createCrops(Long farmId, Crop newCrop) throws FarmNotFoundException {
    Farm farm = farmService.getFarmById(farmId);
    newCrop.setFarm(farm);
    return cropRepository.save(newCrop);
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  public Crop getCropById(Long id) throws CropNotFoundException {
    return cropRepository.findById(id).orElseThrow(CropNotFoundException::new);
  }

  /**
   * Implementation of FindCropsByHarvestDate.
   */
  public List<Crop> findCropsByHarvestDate(LocalDate start, LocalDate end) {
    List<Crop> crops = getAllCrops();

    List<Crop> cropsFiltered = crops.stream()
            .filter((harvestDate) -> harvestDate.getHarvestDate().isAfter(start)
                    && harvestDate.getHarvestDate().isBefore(end)).toList();

    return cropsFiltered;
  }

  /**
   * Implementation of the association between crops and fertilizers.
   */
  public void associateCropFertilizer(Long cropId, Long fertilizerId) throws CropNotFoundException,
      FertilizerNotFoundException {
    Crop crop = getCropById(cropId);
    Fertilizer fertilizer = fertilizerService.getFertilizerById(fertilizerId);

    crop.getFertilizers().add(fertilizer);
    fertilizer.getCrops().add(crop);

    cropRepository.save(crop);
  }

  /**
   * Implementation of Get Route to get all fertilizers by crop id.
   */
  public List<Fertilizer> getFertilizersByCropId(Long id) throws CropNotFoundException {
    Crop crop = getCropById(id);

    return crop.getFertilizers();
  }


}
