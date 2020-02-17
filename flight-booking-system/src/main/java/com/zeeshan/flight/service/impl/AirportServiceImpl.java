package com.zeeshan.flight.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zeeshan.flight.model.Airport;
import com.zeeshan.flight.repository.AirportRepository;
import com.zeeshan.flight.service.AirportService;

@Service
public class AirportServiceImpl implements AirportService {

	@Autowired
	private AirportRepository airportRepository;

	@Override
	public Page<Airport> getAllAirportPaged(int pageNum) {

		return airportRepository.findAll(PageRequest.of(pageNum, 5, Sort.by("airportName")));
	}

	@Override
	public List<Airport> getAllAirports() {

		return airportRepository.findAll();
	}

	@Override
	public Airport getAirportById(Integer airportId) {

		return airportRepository.findById(airportId).orElse(null);
	}

	@Override
	public Airport saveAirport(Airport airport) {

		return airportRepository.save(airport);
	}

	@Override
	public void deleteAirportById(Integer airportId) {

		airportRepository.deleteById(airportId);

	}

}
