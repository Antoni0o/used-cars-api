package com.usedcarsapi.car.usecases;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.dtos.CreateCarRequestDTO;

public interface CreateCarUseCase {
  Car execute(CreateCarRequestDTO request);
}
