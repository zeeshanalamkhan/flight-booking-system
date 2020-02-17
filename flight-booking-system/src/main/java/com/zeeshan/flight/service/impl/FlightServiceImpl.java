package com.zeeshan.flight.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zeeshan.flight.model.Airport;
import com.zeeshan.flight.model.Flight;
import com.zeeshan.flight.repository.FlightRepository;
import com.zeeshan.flight.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepository;

	@Override
	public Page<Flight> getAllFlightPaged(int pageNum) {

		return flightRepository.findAll(PageRequest.of(pageNum, 5, Sort.by("departureAirport")));
	}

	@Override
	public List<Flight> getAllFlights() {

		return flightRepository.findAll();
	}

	@Override
	public Flight getFlightById(Long flightId) {

		return flightRepository.findById(flightId).orElse(null);
	}

	@Override
	public Flight saveFlight(Flight flight) {

		return flightRepository.save(flight);
	}

	@Override
	public void deleteFlightById(Long flightId) {

		flightRepository.deleteById(flightId);

	}

	@Override
	public List<Flight> getAllFlightsByAirportAndDepartureTime(Airport departureAirport, Airport destinationAiirport,
			LocalDate departureDate) {

		return flightRepository.findAllByDepartureAirportEqualsAndDestinationAirportEqualsAndDepartureDateEquals(
				departureAirport, destinationAiirport, departureDate);
	}

}
