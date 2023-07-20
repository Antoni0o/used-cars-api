package com.usedcarsapi.car.usecases.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.dtos.CarRequestDTO;
import com.usedcarsapi.car.ports.CarRepository;
import com.usedcarsapi.car.usecases.UpdateCarUseCase;
import com.usedcarsapi.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateCarUseCaseImpl implements UpdateCarUseCase {
  @Autowired
  private CarRepository repository;

  @Override
  public Car execute(Long carId, CarRequestDTO request) throws NotFoundException {
    log.info("[UpdateCarUseCase:execute] - Starting to update Car");
    Optional<Car> car = repository.findById(carId);

    if (car.isEmpty()) {
      log.error("[UpdateCarUseCase:execute] - Car not found by id when trying to update");
      throw new NotFoundException("Car not found");
    }

    car.get().setBrand(request.getBrand() == "" ? car.get().getBrand() : request.getBrand());
    car.get().setModel(request.getModel() == "" ? car.get().getModel() : request.getModel());
    car.get().setPrice(request.getPrice().compareTo(BigDecimal.ZERO) <= 0 ? car.get().getPrice() : request.getPrice());

    repository.save(car.get());

    log.info("[UpdateCarUseCase:execute] - Car updated successfully");
    return car.get();
  }
}
