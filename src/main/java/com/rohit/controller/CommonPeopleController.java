package com.rohit.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rohit.helper.Message;
import com.rohit.model.TrafficViolator;
import com.rohit.services.CommonPeopleServices;

import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/common-people")
public class CommonPeopleController {

	private final CommonPeopleServices commonPeopleServices;

	@GetMapping("/my-offense")
	public List<TrafficViolator> searchByEmail(Principal principal) {
		return commonPeopleServices.searchTrafficViolationByEmail(principal.getName());
	}

	@PostMapping("/traffic-violator-by-email")
	public List<TrafficViolator> searchByEmail(@RequestParam("email") String email) {
		return commonPeopleServices.searchTrafficViolationByEmail(email);
	}

	@PostMapping("/traffic-violator-by-dl")
	public List<TrafficViolator> searchByDrivingLicenceNumber(
			@RequestParam("drivingLicenceNumber") String drivingLicenceNumber) {
		return commonPeopleServices.searchByDrivingLicence(drivingLicenceNumber);
	}

	@PostMapping("/traffic-violator-by-reg")
	public List<TrafficViolator> searchByRegistrationNumber(@RequestParam("regNumber") String regNumber) {
		return commonPeopleServices.seachByRegistrationNumber(regNumber);
	}

	@PostMapping("/fine-payment")
	public ResponseEntity<Message> finePayment(@RequestParam("violatorid") long violatorId, Principal principal) {
		if (commonPeopleServices.finePaymet(principal.getName(), violatorId)) {
			return ResponseEntity.status(HttpStatus.OK).body(new Message("Payment Successful !!", "success"));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new Message("Payment Failed Try Again !!", "danger"));
		}
	}
}