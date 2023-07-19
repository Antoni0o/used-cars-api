package com.usedcarsapi.car.usecases.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.ports.CarRepository;
import com.usedcarsapi.car.usecases.FindCarByIdUseCase;
import com.usedcarsapi.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindCarByIdUseCaseImpl implements FindCarByIdUseCase {
  @Autowired
  private CarRepository repository;

  @Override
  public Car execute(Long carId) throws NotFoundException {
    log.info("[FindCarByIdUseCase:execute] - Starting to find Car by Id");
    Optional<Car> car = repository.findById(carId);

    if (car.isEmpty()) {
      log.error("[FindCarByIdUseCase:execute] - No Cars found when trying to find by Id!");
      throw new NotFoundException("Car not found!");
    }

    log.info("[FindCarByIdUseCase:execute] - Car found was found by Id");
    return car.get();
  }
}
