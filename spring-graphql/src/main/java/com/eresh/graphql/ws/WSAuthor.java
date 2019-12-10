package com.eresh.graphql.ws;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Created By Gorantla, Eresh on 09/Dec/2019
 **/
@Getter
@Setter
@NoArgsConstructor
public class WSAuthor {

	private String id;

	private String name;

	private LocalDate dateOfBirth;

	private Integer age;

	private String emailId;

	private String mobileNumber;

	private String createdDate;

	private String updatedDate;

}
