package com.usedcarsapi.car.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreateCarRequestDTO {
  private String model;
  private String brand;
  private BigDecimal price;
  private String photoUrl;
}
