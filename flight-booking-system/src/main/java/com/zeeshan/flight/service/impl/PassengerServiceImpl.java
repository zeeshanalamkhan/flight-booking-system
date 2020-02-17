package com.zeeshan.flight.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zeeshan.flight.model.Passenger;
import com.zeeshan.flight.repository.PassengerRepository;
import com.zeeshan.flight.service.PassengerService;

@Service
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerRepository passengerRepository;

	@Override
	public Page<Passenger> getAllPassengerPaged(int pageNum) {

		return passengerRepository.findAll(PageRequest.of(pageNum, 5, Sort.by("lastName")));
	}

	@Override
	public List<Passenger> getAllPassengers() {

		return passengerRepository.findAll();
	}

	@Override
	public Passenger getPassengerById(Integer passengerId) {

		return passengerRepository.findById(passengerId).orElse(null);
	}

	@Override
	public Passenger savePassenger(Passenger passenger) {

		return passengerRepository.save(passenger);
	}

	@Override
	public void deletePassenger(Integer passengerId) {

		passengerRepository.deleteById(passengerId);

	}

}
