package com.usedcarsapi.car.usecases;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.dtos.CreateCarRequestDTO;
import com.usedcarsapi.car.exceptions.NotFoundException;

public interface UpdateCarUseCase {
  Car execute(Long carId, CreateCarRequestDTO request) throws NotFoundException;
}
