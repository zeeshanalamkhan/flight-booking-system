package com.zeeshan.flight.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeeshan.flight.model.Airport;
import com.zeeshan.flight.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	public List<Flight> findAllByDepartureAirportEqualsAndDestinationAirportEqualsAndDepartureDateEquals(
			Airport departureAirport, Airport destinationAirport, LocalDate departureDate);
}
