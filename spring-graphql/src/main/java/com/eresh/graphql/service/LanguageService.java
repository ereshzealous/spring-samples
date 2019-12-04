package com.eresh.graphql.service;

import com.eresh.graphql.persistence.entity.Language;
import com.eresh.graphql.persistence.repository.LanguageRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created By Gorantla, Eresh on 03/Dec/2019
 **/
@Service
@GraphQLApi
public class LanguageService {

	@Autowired
	LanguageRepository languageRepository;

	@GraphQLMutation(name = "saveLanguage")
	public Language saveLanguage(@GraphQLArgument(name = "language") Language language) {
		if (StringUtils.isBlank(language.getId())) {
			Language existingLanguage = languageRepository.findByShortCodeAndName(language.getShortCode(), language.getName());
			if (existingLanguage != null) {
				throw new RuntimeException("Language already exists in the system");
			}
		}
		return languageRepository.save(language);
	}
}
