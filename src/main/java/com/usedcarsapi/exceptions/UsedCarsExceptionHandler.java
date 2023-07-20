package com.usedcarsapi.exceptions;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UsedCarsExceptionHandler {

  @ExceptionHandler(Throwable.class)
  @ResponseBody
  public ResponseEntity<String> handleControllerException(Throwable ex) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    if (ex instanceof AccessDeniedException) {
      status = HttpStatus.FORBIDDEN;
    }

    if (ex instanceof NotFoundException) {
      status = HttpStatus.NOT_FOUND;
    }

    if (ex instanceof BadRequestException) {
      status = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<>(ex.getMessage(), status);
  }
}
