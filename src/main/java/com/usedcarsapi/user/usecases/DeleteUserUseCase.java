package com.usedcarsapi.user.usecases;

import com.usedcarsapi.exceptions.NotFoundException;

public interface DeleteUserUseCase {
  void execute(Long userId) throws NotFoundException;
}
