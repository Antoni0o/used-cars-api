package com.usedcarsapi.car.usecases.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.exceptions.NotFoundException;
import com.usedcarsapi.car.ports.CarRepository;
import com.usedcarsapi.car.usecases.InsertCarPhotoUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InsertCarPhotoUseCaseImpl implements InsertCarPhotoUseCase {
  @Value("${base.url}")
  private String baseUrl;

  @Autowired
  private CarRepository repository;

  @Override
  public void execute(Long carId, MultipartFile file) throws NotFoundException {
    log.info("[InsertCarPhotoUseCase:execute] - Starting to update Car photo");

    Optional<Car> car = repository.findById(carId);

    if (car.isEmpty()) {
      throw new NotFoundException("Car not found");
    }

    String originalFilename = file.getOriginalFilename();
    int extI = originalFilename.lastIndexOf(".");
    String newFilename = car.get().getBrand() + car.get().getModel().replace(" ", "-") + carId + "."
        + originalFilename.substring(extI + 1);
    File finalPath = new File("src/main/resources/static/" + newFilename);

    log.info("[InsertCarPhotoUseCase:execute] - Photo path: " + finalPath);

    try {
      finalPath.createNewFile();
      FileOutputStream fos = new FileOutputStream(finalPath);
      fos.write(file.getBytes());
      fos.close();

      String photoUrl = baseUrl + "/" + newFilename;
      car.get().setPhotoUrl(photoUrl);

      log.info("[InsertCarPhotoUseCase:execute] - Photo URL: " + photoUrl);

      repository.save(car.get());
    } catch (Exception e) {
      log.error("[InsertCarPhotoUseCase:execute] - Error while saving photo " + e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
  }

}
