package com.usedcarsapi.user.usecases.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usedcarsapi.exceptions.NotFoundException;
import com.usedcarsapi.user.User;
import com.usedcarsapi.user.ports.UserRepository;
import com.usedcarsapi.user.usecases.FindUserByIdUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindUserByIdUseCaseImpl implements FindUserByIdUseCase {

  @Autowired
  private UserRepository repository;

  @Override
  public User execute(Long userId) throws NotFoundException {
    log.info("[FindUserByIdUseCase:execute] - Starting to find User by Id");
    Optional<User> user = repository.findById(userId);

    if (user.isEmpty()) {
      log.error("[FindUserByIdUseCase:execute] - User not found when trying to find by Id");
      throw new NotFoundException("User not found");
    }

    log.info("[FindUserByIdUseCase:execute] - User found by Id successfully");
    return user.get();
  }

}
