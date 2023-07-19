package com.usedcarsapi.user.usecases.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usedcarsapi.user.User;
import com.usedcarsapi.user.dtos.UserRequestDTO;
import com.usedcarsapi.user.ports.UserRepository;
import com.usedcarsapi.user.usecases.CreateUserUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUserUseCaseImpl implements CreateUserUseCase {

  @Autowired
  private UserRepository repository;

  @Override
  public User execute(UserRequestDTO request) {
    log.info("[CreateUserUseCase:execute] - Starting to create User");
    User user = new User();
    user.setName(request.getName());
    user.setPassword(request.getPassword());
    user.setAdmin(request.isAdmin());

    repository.save(user);

    log.info("[CreateUserUseCase:execute] - User created successfully!");
    return user;
  }

}
