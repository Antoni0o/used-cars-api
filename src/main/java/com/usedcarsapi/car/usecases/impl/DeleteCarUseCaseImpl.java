package com.usedcarsapi.car.usecases.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.ports.CarRepository;
import com.usedcarsapi.car.usecases.DeleteCarUseCase;
import com.usedcarsapi.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteCarUseCaseImpl implements DeleteCarUseCase {
  @Autowired
  private CarRepository repository;

  @Override
  public void execute(Long carId) throws NotFoundException {
    log.info("[DeleteCarUseCase:execute] - Starting to delete Car");
    Optional<Car> car = repository.findById(carId);

    if (car.isEmpty()) {
      log.error("[DeleteCarUseCase:execute] - Car not found when trying to delete");
      throw new NotFoundException("Car not found!");
    }

    repository.delete(car.get());
    log.info("[DeleteCarUseCase:execute] - Car deleted successfully");
  }

}
