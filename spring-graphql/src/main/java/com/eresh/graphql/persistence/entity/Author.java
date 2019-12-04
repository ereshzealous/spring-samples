package com.eresh.graphql.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;

/**
 * Created By Gorantla, Eresh on 03/Dec/2019
 **/
@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
public class Author {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "age")
	private Integer age;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "created_date", updatable = false)
	private ZonedDateTime createdDate = ZonedDateTime.now();

	@UpdateTimestamp
	@Column(name = "updated_date", insertable = false)
	private ZonedDateTime updatedDate;

	public void setAge(Integer age) {
		if (this.dateOfBirth != null) {
			Period period = Period.between(this.dateOfBirth, LocalDate.now());
			this.age = period.getYears();
		} else {
			this.age = NumberUtils.INTEGER_ZERO;
		}
	}

	public Integer getAge() {
		if (this.age == null) {
			if (this.dateOfBirth != null) {
				Period period = Period.between(this.dateOfBirth, LocalDate.now());
				return period.getYears();
			} else {
				return NumberUtils.INTEGER_ZERO;
			}
		}
		return this.age;
	}
}
