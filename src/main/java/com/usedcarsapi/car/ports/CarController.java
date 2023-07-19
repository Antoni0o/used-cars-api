package com.usedcarsapi.car.ports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.usedcarsapi.car.Car;
import com.usedcarsapi.car.dtos.CarRequestDTO;
import com.usedcarsapi.car.usecases.CreateCarUseCase;
import com.usedcarsapi.car.usecases.DeleteCarUseCase;
import com.usedcarsapi.car.usecases.FindAllCarsUseCase;
import com.usedcarsapi.car.usecases.FindCarByIdUseCase;
import com.usedcarsapi.car.usecases.InsertCarPhotoUseCase;
import com.usedcarsapi.car.usecases.UpdateCarUseCase;
import com.usedcarsapi.exceptions.NotFoundException;

@RestController
@RequestMapping("/car")
public class CarController {
  @Autowired
  private CreateCarUseCase createCarUseCase;

  @Autowired
  private UpdateCarUseCase updateCarUseCase;

  @Autowired
  private DeleteCarUseCase deleteCarUseCase;

  @Autowired
  private FindAllCarsUseCase findAllCarsUseCase;

  @Autowired
  private InsertCarPhotoUseCase insertCarPhotoUseCase;

  @Autowired
  private FindCarByIdUseCase findCarByIdUseCase;

  @GetMapping
  public List<Car> findAllCars() {
    try {
      return findAllCarsUseCase.execute();
    } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @GetMapping("/{carId}")
  public Car findCarById(@PathVariable Long carId) {
    try {
      return findCarByIdUseCase.execute(carId);
    } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Car createCar(@RequestBody CarRequestDTO request) {
    try {
      return createCarUseCase.execute(request);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PutMapping("/{carId}")
  public Car updateCar(@PathVariable Long carId, @RequestBody CarRequestDTO request) {
    try {
      return updateCarUseCase.execute(carId, request);
    } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @DeleteMapping("/{carId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCar(@PathVariable Long carId) {
    try {
      deleteCarUseCase.execute(carId);
    } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PatchMapping("/{carId}")
  public void inserCarPhoto(@PathVariable Long carId, @RequestParam("file") MultipartFile file) {
    try {
      insertCarPhotoUseCase.execute(carId, file);
    } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }
}
