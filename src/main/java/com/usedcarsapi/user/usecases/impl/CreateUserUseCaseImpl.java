package com.usedcarsapi.user.usecases.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.usedcarsapi.exceptions.BadRequestException;
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

  private BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public User execute(UserRequestDTO request) throws BadRequestException {
    log.info("[CreateUserUseCase:execute] - Starting to create User");
    User user = new User();
    UserDetails userWithUsername = repository.findByUsername(request.getName());

    if (userWithUsername.getUsername() != "") {
      log.error("[UpdateUserUseCase:execute] - User already exists with this username");
      throw new BadRequestException("Username already exists");
    }

    user.setUsername(request.getName());
    user.setPassword(passwordEncoder().encode(request.getPassword()));

    repository.save(user);

    log.info("[CreateUserUseCase:execute] - User created successfully!");
    return user;
  }

}
