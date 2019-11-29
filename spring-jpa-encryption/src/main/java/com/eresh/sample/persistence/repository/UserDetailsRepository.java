package com.eresh.sample.persistence.repository;

import com.eresh.sample.persistence.entity.UserDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By Gorantla, Eresh on 29/Nov/2019
 **/
@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {

}
