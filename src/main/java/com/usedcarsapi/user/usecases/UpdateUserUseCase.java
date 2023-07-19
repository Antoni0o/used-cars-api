package com.usedcarsapi.user.usecases;

import com.usedcarsapi.exceptions.NotFoundException;
import com.usedcarsapi.user.User;
import com.usedcarsapi.user.dtos.UserRequestDTO;

public interface UpdateUserUseCase {
  User execute(Long userId, UserRequestDTO request) throws NotFoundException;
}
