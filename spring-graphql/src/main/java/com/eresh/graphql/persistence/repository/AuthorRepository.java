package com.eresh.graphql.persistence.repository;

import com.eresh.graphql.persistence.entity.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Gorantla, Eresh on 03/Dec/2019
 **/
@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

	Author findByEmailId(String emailId);

	Author findByMobileNumber(String mobileNumber);

	List<Author> findByNameContains(String name);

	List<Author> findByIdIn(List<String> ids);
}
