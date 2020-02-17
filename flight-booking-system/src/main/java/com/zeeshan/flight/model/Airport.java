package com.zeeshan.flight.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AIRPORT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer airportId;
	private String airportCode;
	private String airportName;
	private String city;
	private String state;
	private String country;

	@OneToMany(mappedBy = "departureAirport", cascade = { CascadeType.ALL })
	private List<Flight> flights = new ArrayList<Flight>();

	@OneToMany(mappedBy = "flight", cascade = { CascadeType.ALL })
	private List<Passenger> passengers = new ArrayList<Passenger>();

}
