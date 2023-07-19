package com.usedcarsapi.user.ports;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usedcarsapi.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
