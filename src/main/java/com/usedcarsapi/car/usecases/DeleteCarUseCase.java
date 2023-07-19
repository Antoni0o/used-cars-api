package com.usedcarsapi.car.usecases;

import com.usedcarsapi.exceptions.NotFoundException;

public interface DeleteCarUseCase {
  void execute(Long carId) throws NotFoundException;
}
