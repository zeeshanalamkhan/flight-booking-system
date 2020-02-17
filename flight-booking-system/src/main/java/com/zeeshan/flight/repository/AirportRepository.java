package com.zeeshan.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeeshan.flight.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

}
