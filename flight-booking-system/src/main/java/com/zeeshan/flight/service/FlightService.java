package com.zeeshan.flight.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import com.zeeshan.flight.model.Airport;
import com.zeeshan.flight.model.Flight;

public interface FlightService {

	public Page<Flight> getAllFlightPaged(int pageNum);

	public List<Flight> getAllFlights();

	public Flight getFlightById(Long flightId);

	public Flight saveFlight(Flight flight);

	public void deleteFlightById(Long flightId);

	public List<Flight> getAllFlightsByAirportAndDepartureTime(Airport departureAiirport, Airport destinationAiirport,
			LocalDate departureDate);

}
