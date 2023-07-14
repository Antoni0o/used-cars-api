package com.usedcarsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usedcarsapi.entities.User;

public interface ICarRepository extends JpaRepository<User, Long> {
}
