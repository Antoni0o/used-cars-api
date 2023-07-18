package com.usedcarsapi.car.usecases.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.dtos.CreateCarRequestDTO;
import com.usedcarsapi.car.exceptions.NotFoundException;
import com.usedcarsapi.car.ports.CarRepository;
import com.usedcarsapi.car.usecases.CreateCarUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateCarUseCaseImpl implements CreateCarUseCase {
  @Autowired
  private CarRepository repository;

  @Override
  public Car execute(CreateCarRequestDTO request) throws NotFoundException {
    log.info("[CreateCarUseCase:execute] - Starting to create Car");
    Car car = new Car();
    car.setBrand(request.getBrand());
    car.setModel(request.getModel());
    car.setPrice(request.getPrice());

    repository.save(car);

    log.info("[CreateCarUseCase:execute] - Car created successfully!");
    return car;
  }
}
