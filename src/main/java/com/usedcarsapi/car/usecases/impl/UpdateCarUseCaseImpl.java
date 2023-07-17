package com.usedcarsapi.car.usecases.impl;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.dtos.CreateCarRequestDTO;
import com.usedcarsapi.car.exceptions.NotFoundException;
import com.usedcarsapi.car.ports.CarRepository;
import com.usedcarsapi.car.usecases.UpdateCarUseCase;

public class UpdateCarUseCaseImpl implements UpdateCarUseCase {
  @Autowired
  private CarRepository repository;
  Logger logger = Logger.getLogger(CreateCarUseCaseImpl.class.getName());

  @Override
  public Car execute(Long carId, CreateCarRequestDTO request) throws NotFoundException {
    logger.info("[UpdateCarUseCase:execute] - Starting to update Car");
    Optional<Car> car = repository.findById(carId);

    if (car.isEmpty()) {
      logger.warning("[UpdateCarUseCase:execute] - Car not found by id when trying to update");
      throw new NotFoundException("Car not found");
    }

    car.get().setBrand(request.getBrand() == "" ? car.get().getBrand() : request.getBrand());
    car.get().setBrand(request.getBrand() == "" ? car.get().getBrand() : request.getBrand());
    car.get().setModel(request.getModel() == "" ? car.get().getModel() : request.getModel());
    car.get().setPhotoUrl(request.getPhotoUrl() == "" ? car.get().getPhotoUrl() : request.getPhotoUrl());
    car.get().setPrice(request.getPrice() == new BigDecimal(0) ? car.get().getPrice() : request.getPrice());

    repository.save(car.get());

    return car.get();
  }

}
