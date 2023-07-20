package com.usedcarsapi.user.ports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.usedcarsapi.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
  UserDetails findByUsername(String username);
}
