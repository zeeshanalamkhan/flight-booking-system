package com.zeeshan.flight.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeeshan.flight.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String username);
}
