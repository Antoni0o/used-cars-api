package com.usedcarsapi.car.usecases.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.exceptions.NotFoundException;
import com.usedcarsapi.car.ports.CarRepository;
import com.usedcarsapi.car.usecases.FindAllCarsUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindAllCarsUseCaseImpl implements FindAllCarsUseCase {
  @Autowired
  private CarRepository repository;

  @Override
  public List<Car> execute() throws NotFoundException {
    log.info("[FindAllCarsUseCase:execute] - Starting to find all Cars");
    List<Car> cars = repository.findAll(Sort.by(Sort.Direction.DESC, "price"));

    if (cars.isEmpty()) {
      log.error("[FindAllCarsUseCase:execute] - No Cars found when trying to find all cars!");
      throw new NotFoundException("No Cars found!");
    }

    log.info("[FindAllCarsUseCase:execute] - All cars found");
    return cars;
  }
}
