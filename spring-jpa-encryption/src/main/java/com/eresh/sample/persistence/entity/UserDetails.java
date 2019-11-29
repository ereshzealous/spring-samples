package com.eresh.sample.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

/**
 * Created By Gorantla, Eresh on 29/Nov/2019
 **/
@Getter
@Setter
@Entity
@Table(name = "user_details")
public class UserDetails {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@ColumnTransformer(forColumn = "emailId", read = "pgp_sym_decrypt(age, 'password')", write = "pgp_sym_encrypt(?, 'password')")
	@Column(name = "emailId", columnDefinition = "bytea")
	private String emailId;

	@ColumnTransformer(forColumn = "mobile_number", read = "pgp_sym_decrypt(age, 'password')", write = "pgp_sym_encrypt(?, 'password')")
	@Column(name = "mobile_number", columnDefinition = "bytea")
	private String mobileNumber;

	@ColumnTransformer(forColumn = "first_name", read = "pgp_sym_decrypt(age, 'password')", write = "pgp_sym_encrypt(?, 'password')")
	@Column(name = "first_name", columnDefinition = "bytea")
	private String firstName;

	@ColumnTransformer(forColumn = "last_name", read = "pgp_sym_decrypt(age, 'password')", write = "pgp_sym_encrypt(?, 'password')")
	@Column(name = "last_name", columnDefinition = "bytea")
	private String lastName;

	@ColumnTransformer(forColumn = "address", read = "pgp_sym_decrypt(age, 'password')", write = "pgp_sym_encrypt(?, 'password')")
	@Column(name = "address", columnDefinition = "bytea")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "created_date", updatable = false)
	private ZonedDateTime createdDate = ZonedDateTime.now();

	@Column(name = "updated_date", insertable = false)
	@UpdateTimestamp
	private ZonedDateTime updatedDate;
}
