package com.usedcarsapi.car.usecases;

import java.util.List;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.exceptions.NotFoundException;

public interface FindAllCarsUseCase {
  List<Car> execute() throws NotFoundException;
}
