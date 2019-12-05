package com.eresh.wiremock.controller.ws;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Created By Gorantla, Eresh on 04/Dec/2019
 **/
@Getter
@Setter
@NoArgsConstructor
public class WSUserDetails extends RestResponse {
	private String id;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String city;
	private String country;
}
