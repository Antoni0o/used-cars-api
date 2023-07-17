package com.usedcarsapi.car.ports;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usedcarsapi.car.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
