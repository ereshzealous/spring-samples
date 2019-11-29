package com.eresh.sample.controller;

import com.eresh.sample.controller.ws.WSUserDetails;
import com.eresh.sample.persistence.entity.UserDetails;
import com.eresh.sample.persistence.repository.UserDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Gorantla, Eresh on 29/Nov/2019
 **/
@RestController("/api")
public class UserDetailsController {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@PutMapping("/user")
	public ResponseEntity<UserDetails> saveUserDetails(@RequestBody WSUserDetails request) {
		UserDetails userDetails = new UserDetails();
		userDetails.setAddress(request.getAddress());
		userDetails.setCity(request.getCity());
		userDetails.setEmailId(request.getEmailId());
		userDetails.setFirstName(request.getFirstName());
		userDetails.setLastName(request.getLastName());
		userDetails.setMobileNumber(request.getMobileNumber());
		return ResponseEntity.ok(userDetailsRepository.save(userDetails));
	}
}
