package com.zeeshan.flight.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.zeeshan.flight.model.Passenger;

public interface PassengerService {

	public Page<Passenger> getAllPassengerPaged(int pageNum);

	public List<Passenger> getAllPassengers();

	public Passenger getPassengerById(Integer passengerId);

	public Passenger savePassenger(Passenger passenger);

	public void deletePassenger(Integer passengerId);

}
