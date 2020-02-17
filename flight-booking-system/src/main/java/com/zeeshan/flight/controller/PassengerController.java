package com.zeeshan.flight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zeeshan.flight.model.Passenger;
import com.zeeshan.flight.service.FlightService;
import com.zeeshan.flight.service.PassengerService;

@Controller
public class PassengerController {

	@Autowired
	private FlightService flightService;

	@Autowired
	private PassengerService passengerService;

	@GetMapping("/passengers")
	public String showPassengersList(@RequestParam("flightId") Long flightId, Model model) {

		List<Passenger> passengers = passengerService.getAllPassengers();
		model.addAttribute("passengers", passengers);
		model.addAttribute("flight", flightService.getFlightById(flightId));
		return "passengers";
	}

}
