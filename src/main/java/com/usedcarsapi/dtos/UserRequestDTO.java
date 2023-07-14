package com.usedcarsapi.dtos;

import lombok.Data;

@Data
public class UserRequestDTO {
  private String name;
  private String password;
  private boolean isAdmin;
}