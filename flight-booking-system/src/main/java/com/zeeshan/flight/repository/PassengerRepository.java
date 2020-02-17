package com.zeeshan.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeeshan.flight.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

}
