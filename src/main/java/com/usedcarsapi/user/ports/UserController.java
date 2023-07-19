package com.usedcarsapi.user.ports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.usedcarsapi.exceptions.NotFoundException;
import com.usedcarsapi.user.User;
import com.usedcarsapi.user.dtos.UserRequestDTO;
import com.usedcarsapi.user.usecases.CreateUserUseCase;
import com.usedcarsapi.user.usecases.DeleteUserUseCase;
import com.usedcarsapi.user.usecases.FindUserByIdUseCase;
import com.usedcarsapi.user.usecases.UpdateUserUseCase;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private CreateUserUseCase createUserUseCase;

  @Autowired
  private UpdateUserUseCase updateUserUseCase;

  @Autowired
  private FindUserByIdUseCase findUserByIdUseCase;

  @Autowired
  private DeleteUserUseCase deleteUserUseCase;

  @GetMapping("/{userId}")
  public User findUserById(@PathVariable Long userId) {
    try {
      return findUserByIdUseCase.execute(userId);
    } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public User createUser(@RequestBody UserRequestDTO request) {
    try {
      return createUserUseCase.execute(request);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PutMapping("/{userId}")
  public User updateUser(@PathVariable Long userId, @RequestBody UserRequestDTO request) {
    try {
      return updateUserUseCase.execute(userId, request);
    } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @DeleteMapping("/{userId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable Long userId) {
    try {
      deleteUserUseCase.execute(userId);
    } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }
}
