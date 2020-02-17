package com.zeeshan.flight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String showHomePage() {
		return "index";
	}

	@GetMapping("/login")
	public String showLoginPage() {

		return "login";
	}

	@GetMapping("/fancy")
	public String showLoginPage1() {

		return "index";
	}
}