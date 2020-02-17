package com.zeeshan.flight.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FLIGHT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long flightId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate arrivalDate;

	private String arrivalTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate departureDate;

	private String departureTime;

	private Double flightCharge;

	private String flightNumber;

	@ManyToOne
	private Aircraft aircraft;

	@ManyToOne
	private Airport departureAirport;

	@ManyToOne
	private Airport destinationAirport;

	@OneToMany(mappedBy = "flight", cascade = { CascadeType.ALL })
	private List<Passenger> passengers = new ArrayList<Passenger>();
}
