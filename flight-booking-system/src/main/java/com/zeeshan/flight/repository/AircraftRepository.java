package com.zeeshan.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeeshan.flight.model.Aircraft;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

}
