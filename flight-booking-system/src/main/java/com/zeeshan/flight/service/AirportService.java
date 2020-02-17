package com.zeeshan.flight.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.zeeshan.flight.model.Airport;

public interface AirportService {

	public Page<Airport> getAllAirportPaged(int pageNum);

	public List<Airport> getAllAirports();

	public Airport getAirportById(Integer airportId);

	public Airport saveAirport(Airport airport);

	public void deleteAirportById(Integer airportId);

}
