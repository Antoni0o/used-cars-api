package com.usedcarsapi.car.usecases.impl;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.exceptions.NotFoundException;
import com.usedcarsapi.car.ports.CarRepository;
import com.usedcarsapi.car.usecases.DeleteCarUseCase;

public class DeleteCarUseCaseImpl implements DeleteCarUseCase {
  @Autowired
  private CarRepository repository;
  Logger logger = Logger.getLogger(CreateCarUseCaseImpl.class.getName());

  @Override
  public void execute(Long carId) throws NotFoundException {
    logger.info("[DeleteCarUseCase:execute] - Starting to delete Car");
    Optional<Car> car = repository.findById(carId);

    if (car.isEmpty()) {
      logger.warning("[DeleteCarUseCase:execute] - Car not found when trying to delete");
      throw new NotFoundException("Car not found!");
    }

    repository.delete(car.get());
    logger.info("[DeleteCarUseCase:execute] - Car deleted successfully");
  }

}
