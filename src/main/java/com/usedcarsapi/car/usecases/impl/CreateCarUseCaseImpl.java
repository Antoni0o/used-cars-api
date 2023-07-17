package com.usedcarsapi.car.usecases.impl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.dtos.CreateCarRequestDTO;
import com.usedcarsapi.car.ports.CarRepository;
import com.usedcarsapi.car.usecases.CreateCarUseCase;

public class CreateCarUseCaseImpl implements CreateCarUseCase {
  @Autowired
  private CarRepository repository;
  Logger logger = Logger.getLogger(CreateCarUseCaseImpl.class.getName());

  @Override
  public Car execute(CreateCarRequestDTO request) {
    logger.info("[CreateCarUseCase:execute] - Starting to create Car");
    Car car = new Car();
    car.setBrand(request.getBrand());
    car.setModel(request.getModel());
    car.setPrice(request.getPrice());
    car.setPhotoUrl(request.getPhotoUrl());

    repository.save(car);

    logger.info("[CreateCarUseCase:execute] - Car created successfully!");
    return car;
  }
}
