package com.usedcarsapi.car.usecases;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.dtos.CreateCarRequestDTO;
import com.usedcarsapi.car.exceptions.NotFoundException;

public interface CreateCarUseCase {
  Car execute(CreateCarRequestDTO request) throws NotFoundException;
}
