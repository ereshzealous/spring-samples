# Spring Boot JPA Data Encryption

There will be seevral use cases to encrypt user data to hide sensitive information like email, mobile, address other details.
In order to achieve above use case consider below two ways.
1. Use any of AES encryption techniques and perform encrypt and decrypt every time call is made on table.
2. At database side, with the help of trigger add own data base encryption technique on column for every insert and update.

Downsides of above two apporaches
1. If any of AES encryption techniques are used it will be eextreemly hard to write select query for search. If you want to search a product by name phone, and you are perfroming wildcard search the data is not matched at all. 
2. The second one is pretty of for inserts, but for retrieving a plain query by passing encryption functions have to be performed. So JPA concepts can not be applied here, spring jdbc template has to be used here.

## Spring JPA feature used to write and read encrypted data from and RDBMS.
Spring Jpa has many concepts one among them is **ColumnTransformer**

### Column Transformer
Custom SQL expression used to read the value from and write a value to a column. Use for direct object loading/saving as well as queries. The write expression must contain exactly one '?' placeholder for the value. For example: read="decrypt(credit_card_num)" write="encrypt(?)"

## Code Sample
In this code sample we have used below configurations
* **Java 8**
* **Spring Boot 2.1.8.Release**
* **PostGresql 12**
* **Postman for testing**
* **Liquibase as ddl creation** (We will have in another tuitorial for details)

### DB Settings
If you are using Postgres, you have to enable few extensions before working on this code sample.
1. pgcrypto -- It is a crypto utility used by postgres.
```sql
CREATE EXTENSION IF NOT EXISTS "pgcrypto"
```
2. uuid-ossp -- I have used UUID as primary key. 
```sql
CREATE EXTENSION IF NOT EXISTS "uuid-ossp"
```
Incase if not interested to use liquibase. Below is the create table sql.
```sql
CREATE TABLE public.user_details
(
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    email_id bytea NOT NULL,
    mobile_number bytea NOT NULL,
    first_name bytea,
    last_name bytea,
    date_of_birth timestamp without time zone,
    city character varying(50) COLLATE pg_catalog."default",
    address bytea,
    created_date timestamp without time zone,
    updated_date timestamp without time zone,
    CONSTRAINT user_details_pkey PRIMARY KEY (id)
)
```

### Datatype is bytea
As there is encryption we have to take right data type supported for data encryption.

### Entity Class
```java
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
```
1. Above we have used pgp_sym_decrypt for both read and write. JPA will apply approprite function when read and writes are called.
2. Here we have given simple clear password. We can either use a function to generate this key inside database or set in postgres conf file.

Now simple repository.save method will insert encrypted data in database. If you fetch the data is decrypted.