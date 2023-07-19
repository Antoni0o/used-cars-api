package com.usedcarsapi.user.usecases.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usedcarsapi.exceptions.NotFoundException;
import com.usedcarsapi.user.User;
import com.usedcarsapi.user.ports.UserRepository;
import com.usedcarsapi.user.usecases.DeleteUserUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

  @Autowired
  private UserRepository repository;

  @Override
  public void execute(Long userId) throws NotFoundException {
    log.info("[DeleteUserUseCase:execute] - Starting to delete User");
    Optional<User> user = repository.findById(userId);

    if (user.isEmpty()) {
      log.error("[DeleteUserUseCase:execute] - User not found when trying to delete");
      throw new NotFoundException("User not found!");
    }

    repository.delete(user.get());
    log.info("[DeleteUserUseCase:execute] - User deleted successfully");
  }

}
