package com.usedcarsapi.car.usecases.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.exceptions.NotFoundException;
import com.usedcarsapi.car.ports.CarRepository;
import com.usedcarsapi.car.usecases.FindAllCarsUseCase;

public class FindAllCarsUseCaseImpl implements FindAllCarsUseCase {
  @Autowired
  private CarRepository repository;
  Logger logger = Logger.getLogger(CreateCarUseCaseImpl.class.getName());

  @Override
  public List<Car> execute() throws NotFoundException {
    logger.info("[FindAllCarsUseCase:execute] - Starting to find all Cars");
    List<Car> cars = repository.findAll(Sort.by(Sort.Direction.DESC, "price"));

    if (cars.isEmpty()) {
      logger.warning("[FindAllCarsUseCase:execute] - No Cars found when trying to find all cars!");
      throw new NotFoundException("No Cars found!");
    }

    logger.info("[FindAllCarsUseCase:execute] - All cars found");
    return cars;
  }

}
