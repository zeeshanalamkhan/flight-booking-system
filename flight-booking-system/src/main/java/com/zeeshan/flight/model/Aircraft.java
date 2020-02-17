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
@Table(name = "AIRCRAFT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aircraft {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long aircraftId;

	private String manufacturer;

	private String model;

	private Integer numberOfSeats;

	@OneToMany(mappedBy = "aircraft", cascade = { CascadeType.ALL })
	private List<Flight> flights = new ArrayList<Flight>();

}
