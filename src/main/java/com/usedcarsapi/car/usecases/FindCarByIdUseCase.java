package com.usedcarsapi.car.usecases;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.exceptions.NotFoundException;

public interface FindCarByIdUseCase {
  Car execute(Long carId) throws NotFoundException;
}
