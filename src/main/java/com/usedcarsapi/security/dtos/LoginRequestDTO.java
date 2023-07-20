package com.usedcarsapi.security.dtos;

import lombok.Data;

@Data
public class LoginRequestDTO {
  private String name;
  private String password;
}
