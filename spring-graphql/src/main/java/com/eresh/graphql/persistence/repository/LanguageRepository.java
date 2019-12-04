package com.eresh.graphql.persistence.repository;

import com.eresh.graphql.persistence.entity.Language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Gorantla, Eresh on 03/Dec/2019
 **/
@Repository
public interface LanguageRepository extends JpaRepository<Language, String> {

	Language findByShortCodeAndName(String shortCode, String name);

	List<Language> findByIdIn(List<String> ids);
}
