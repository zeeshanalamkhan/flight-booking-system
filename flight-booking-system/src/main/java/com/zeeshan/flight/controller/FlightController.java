package com.zeeshan.flight.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zeeshan.flight.model.Airport;
import com.zeeshan.flight.model.Flight;
import com.zeeshan.flight.model.Passenger;
import com.zeeshan.flight.service.AircraftService;
import com.zeeshan.flight.service.AirportService;
import com.zeeshan.flight.service.FlightService;
import com.zeeshan.flight.service.PassengerService;

@Controller
public class FlightController {

	@Autowired
	private AircraftService aircraftService;

	@Autowired
	private AirportService airportService;

	@Autowired
	private FlightService flightService;

	@Autowired
	private PassengerService passengerService;

	@GetMapping("/flight/new")
	public String showNewFlightPage(Model model) {

		model.addAttribute("flight", new Flight());
		model.addAttribute("aircrafts", aircraftService.getAllAircrafts());
		model.addAttribute("airports", airportService.getAllAirports());
		return "newFlight";
	}

	@PostMapping("/flight/new")
	public String saveFlight(@Valid @ModelAttribute("flight") Flight flight, BindingResult bindingResult,
			@RequestParam("departureAirport") Integer departureAirport,
			@RequestParam("destinationAirport") Integer destinationAirport, @RequestParam("aircraft") Long aircraftId,
			@RequestParam("arrivalTime") String arrivalTime, @RequestParam("departureTime") String departureTime,
			Model model) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("errors", bindingResult.getAllErrors());
			model.addAttribute("flight", new Flight());
			model.addAttribute("aircrafts", aircraftService.getAllAircrafts());
			model.addAttribute("airports", airportService.getAllAirports());
			return "newFlight";
		}

		if (departureAirport == destinationAirport) {
			model.addAttribute("sameAirportError", "Departure and Destination Airports can't be same");
			model.addAttribute("flight", new Flight());
			model.addAttribute("aircrafts", aircraftService.getAllAircrafts());
			model.addAttribute("airports", airportService.getAllAirports());
			return "newFlight";
		}

		flight.setAircraft(aircraftService.getAircraftById(aircraftId));
		flight.setDepartureAirport(airportService.getAirportById(departureAirport));
		flight.setDestinationAirport(airportService.getAirportById(destinationAirport));
		flight.setDepartureTime(departureTime);
		flight.setArrivalTime(arrivalTime);
		flightService.saveFlight(flight);

		model.addAttribute("flights", flightService.getAllFlightPaged(0));
		model.addAttribute("currentPage", 0);
		return "flights";

	}

	// Thymeleaf is an HTML template engine. HTML does not support put or delete HTTP methods.
	@GetMapping("/flight/delete")
	public String deleteFlight(@PathParam("flightId") Long flightId, Model model) {

		flightService.deleteFlightById(flightId);
		model.addAttribute("flights", flightService.getAllFlightPaged(0));
		model.addAttribute("currentPage", "0");
		return "flights";

	}

	@GetMapping("/flights")
	public String showFlightsList(@RequestParam(defaultValue = "0") int pageNo, Model model) {

		model.addAttribute("flights", flightService.getAllFlightPaged(pageNo));
		model.addAttribute("currentPage", pageNo);
		return "flights";

	}

	@GetMapping("/flight/search")
	public String searchFlightPage(Model model) {

		model.addAttribute("airports", airportService.getAllAirports());
		model.addAttribute("flights", null);
		return "searchFlight";
	}

	@PostMapping("/flight/search")
	public String searchFlight(@RequestParam("departureAirport") Integer departureAirport,
			@RequestParam("destinationAirport") Integer destinationAirport,
			@RequestParam("departureTime") String departureTime, Model model) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate departureDate = LocalDate.parse(departureTime, dateTimeFormatter);
		Airport depAirport = airportService.getAirportById(departureAirport);
		Airport destAirport = airportService.getAirportById(destinationAirport);

		if (depAirport == destAirport) {
			model.addAttribute("AirportError", "Departure and Destination airport can't be same");
			model.addAttribute("aiports", airportService.getAllAirports());
			return "searchFlight";
		}

		List<Flight> flights = flightService.getAllFlightsByAirportAndDepartureTime(depAirport, destAirport,
				departureDate);
		if (flights.isEmpty()) {
			model.addAttribute("notFound", "No record found");
		} else {
			model.addAttribute("flights", flights);
		}

		model.addAttribute("airports", airportService.getAllAirports());
		return "searchFlight";

	}

	@GetMapping("/flight/book")
	public String showBookFlightPage(Model model) {

		model.addAttribute("airports", airportService.getAllAirports());
		return "bookFlight";
	}

	@PostMapping("/flight/book")
	public String searchFlightToBook(@RequestParam("departureAirport") Integer departureAirport,
			@RequestParam("destinationAirport") Integer destinationAirport,
			@RequestParam("departureTime") String departureTime, Model model) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate depDate = LocalDate.parse(departureTime, dateTimeFormatter);
		Airport depAirport = airportService.getAirportById(departureAirport);
		Airport destAirport = airportService.getAirportById(destinationAirport);

		if (depAirport == destAirport) {

			model.addAttribute("AirportError", "Departure airport and Destination Airport can't be same");
			model.addAttribute("airports", airportService.getAllAirports());
			return "bookFlight";

		}

		List<Flight> flights = flightService.getAllFlightsByAirportAndDepartureTime(depAirport, destAirport, depDate);
		if (flights.isEmpty()) {

			model.addAttribute("notFound", "No record found");

		} else {

			model.addAttribute("flights", flights);
		}

		model.addAttribute("airports", airportService.getAllAirports());
		return "bookFlight";
	}

	@GetMapping("/flight/book/new")
	public String customerInfoPage(@RequestParam("flightId") Long flightId, Model model) {

		model.addAttribute("flightId", flightId);
		model.addAttribute("passenger", new Passenger());
		return "newPassenger";
	}

	@PostMapping("/flight/book/new")
	public String bookFlight(@Valid @ModelAttribute Passenger passenger, BindingResult bindingResult,
			@RequestParam("flightId") Long flightId, Model model) {

		Flight flight = flightService.getFlightById(flightId);
		Passenger passenger1 = passenger;
		passenger1.setFlight(flight);
		passengerService.savePassenger(passenger1);
		model.addAttribute("passenger", passenger1);
		return "confirmationPage";
	}

	@GetMapping("/flight/book/verify")
	public String showVerifyBookingPage() {

		return "verifyBooking";
	}

	@PostMapping("/flight/book/verify")
	public String showVerifyBookingPageResult(@RequestParam("flightId") Long flightId,
			@RequestParam("passengerId") Integer passengerId, Model model) {

		Flight flight = flightService.getFlightById(flightId);
		if (flight != null) {

			model.addAttribute("flight", flight);
			List<Passenger> passengers = flight.getPassengers();
			Passenger passenger = null;
			for (Passenger p : passengers) {

				if (p.getPassengerId() == passengerId) {
					passenger = passengerService.getPassengerById(passengerId);
					model.addAttribute("passenger", passenger);
				}
			}
			if (passenger != null) {

				return "verifyBooking";
			} else {

				model.addAttribute("notFound", "Not Found");
				return "verifyBooking";
			}
		} else {

			model.addAttribute("notFound", "Not Found");
			return "verifyBooking";
		}
	}

	@PostMapping("/flight/book/cancel")
	public String cancelTicket(@RequestParam("passengerId") Integer passengerId, Model model) {

		passengerService.deletePassenger(passengerId);
		model.addAttribute("flights", flightService.getAllFlightPaged(0));
		model.addAttribute("currentPage", 0);
		return "flights";
	}

}
