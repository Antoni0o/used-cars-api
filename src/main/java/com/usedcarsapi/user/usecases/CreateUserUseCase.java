package com.usedcarsapi.user.usecases;

import com.usedcarsapi.exceptions.BadRequestException;
import com.usedcarsapi.user.User;
import com.usedcarsapi.user.dtos.UserRequestDTO;

public interface CreateUserUseCase {
  User execute(UserRequestDTO request) throws BadRequestException;
}
