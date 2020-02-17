package com.zeeshan.flight.controller;

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
import com.zeeshan.flight.service.AirportService;

@Controller
public class AirportController {

	@Autowired
	private AirportService airportService;

	@GetMapping("/airport/new")
	public String showAddAirportPage(Model model) {

		model.addAttribute("airport", new Airport());
		return "newAirport";
	}

	@PostMapping("/airport/new")
	public String saveAirport(@Valid @ModelAttribute("airport") Airport airport, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("errors", bindingResult.getAllErrors());
			model.addAttribute("airport", new Airport());
			return "newAirport";
		}
		airportService.saveAirport(airport);
		model.addAttribute("airports", airportService.getAllAirportPaged(0));
		model.addAttribute("currentPage", 0);
		return "airports";
	}

	// Thymeleaf is an HTML template engine. HTML does not support put or delete HTTP methods.
	@GetMapping("/airport/delete")
	public String deleteAirport(@PathParam("airportId") Integer airportId, Model model) {

		airportService.deleteAirportById(airportId);
		model.addAttribute("airports", airportService.getAllAirportPaged(0));
		model.addAttribute("currentPage", 0);
		return "airports";
	}

	@GetMapping("/airports")
	public String showAirportsList(@RequestParam(defaultValue = "0") int pageNo, Model model) {

		model.addAttribute("airports", airportService.getAllAirportPaged(pageNo));
		model.addAttribute("currentPage", pageNo);
		return "airports";
	}

}
