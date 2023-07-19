package com.usedcarsapi.car.usecases;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.dtos.CarRequestDTO;
import com.usedcarsapi.exceptions.NotFoundException;

public interface UpdateCarUseCase {
  Car execute(Long carId, CarRequestDTO request) throws NotFoundException;
}
