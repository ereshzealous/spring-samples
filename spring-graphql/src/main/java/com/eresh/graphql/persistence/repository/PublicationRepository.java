package com.eresh.graphql.persistence.repository;

import com.eresh.graphql.persistence.entity.Publication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Gorantla, Eresh on 03/Dec/2019
 **/
@Repository
public interface PublicationRepository extends JpaRepository<Publication, String> {

	Publication findByRegistration(String registration);

	List<Publication> findByNameContains(String name);

	List<Publication> findByEmailAddress(String emailAddress);

	List<Publication> findByIdIn(List<String> ids);
}
