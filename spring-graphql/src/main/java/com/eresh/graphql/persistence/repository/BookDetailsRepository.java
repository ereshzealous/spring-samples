package com.eresh.graphql.persistence.repository;

import com.eresh.graphql.persistence.entity.BookDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By Gorantla, Eresh on 03/Dec/2019
 **/
@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails, String> {

}
