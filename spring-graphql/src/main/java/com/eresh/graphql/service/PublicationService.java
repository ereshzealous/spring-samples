package com.eresh.graphql.service;

import com.eresh.graphql.persistence.entity.Publication;
import com.eresh.graphql.persistence.repository.PublicationRepository;
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
public class PublicationService {

	@Autowired
	PublicationRepository publicationRepository;

	@GraphQLMutation(name = "savePublication")
	public Publication savePublication(@GraphQLArgument(name = "publication") Publication publication) {
		if (StringUtils.isBlank(publication.getId())) {
			Publication publicationExists = publicationRepository.findByRegistration(publication.getRegistration());
			if (publicationExists != null) {
				throw new RuntimeException(("This publication is already registered"));
			}
		}
		return publicationRepository.save(publication);
	}
}
