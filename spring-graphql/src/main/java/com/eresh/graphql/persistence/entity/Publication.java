package com.eresh.graphql.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * Created By Gorantla, Eresh on 03/Dec/2019
 **/
@Entity
@Table(name = "publication")
@Getter
@Setter
@NoArgsConstructor
public class Publication {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "address")
	private String address;

	@Column(name = "site_link")
	private String siteLink;

	@Column(name = "registration")
	private String registration;

	@Column(name = "establishment_date")
	private LocalDate establishmentDate;

	@Column(name = "email_address")
	private String emailAddress;

	@Column(name = "contact_numbers")
	private String contactNumbers;

	@Column(name = "created_date", updatable = false)
	private ZonedDateTime createdDate = ZonedDateTime.now();

	@UpdateTimestamp
	@Column(name = "updated_date", insertable = false)
	private ZonedDateTime updatedDate;
}
