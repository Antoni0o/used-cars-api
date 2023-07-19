package com.usedcarsapi.user.usecases;

import com.usedcarsapi.exceptions.NotFoundException;
import com.usedcarsapi.user.User;

public interface FindUserByIdUseCase {
  User execute(Long userId) throws NotFoundException;
}
