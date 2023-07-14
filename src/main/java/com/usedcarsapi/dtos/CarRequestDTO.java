package com.usedcarsapi.dtos;

import lombok.Data;

@Data
public class CarRequestDTO {
  private String model;
  private String brand;
  private String photoUrl;
}
