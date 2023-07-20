package com.usedcarsapi.user.usecases.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usedcarsapi.exceptions.NotFoundException;
import com.usedcarsapi.user.User;
import com.usedcarsapi.user.dtos.UserRequestDTO;
import com.usedcarsapi.user.ports.UserRepository;
import com.usedcarsapi.user.usecases.UpdateUserUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

  @Autowired
  private UserRepository repository;

  @Override
  public User execute(Long userId, UserRequestDTO request) throws NotFoundException {
    log.info("[UpdateUserUseCase:execute] - Starting to update User");
    Optional<User> user = repository.findById(userId);

    if (user.isEmpty()) {
      log.error("[UpdateUserUseCase:execute] - User not found when trying to update");
      throw new NotFoundException("User not found");
    }

    user.get().setUsername(request.getName() == "" ? user.get().getUsername() : request.getName());
    user.get().setPassword(request.getPassword() == "" ? user.get().getPassword() : request.getPassword());

    repository.save(user.get());

    log.info("[UpdateUserUseCase:execute] - User updated successfully");
    return user.get();
  }

}
