package com.usedcarsapi.car.usecases;

import org.springframework.web.multipart.MultipartFile;

import com.usedcarsapi.exceptions.NotFoundException;

public interface InsertCarPhotoUseCase {
  void execute(Long carId, MultipartFile file) throws NotFoundException;
}