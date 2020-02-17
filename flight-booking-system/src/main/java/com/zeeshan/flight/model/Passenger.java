package com.zeeshan.flight.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PASSENGER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer passengerId;

	private String address;

	private String email;

	@NotBlank(message = "* First Name is required")
	private String firstName;

	@NotBlank(message = "* Last Name is required")
	private String lastName;

	@NotBlank(message = "* Passport Number is required")
	private String passportNumber;

	@NotBlank(message = "* Phone Number is required")
	private String phoneNumber;

	@ManyToOne
	private Flight flight;

}
