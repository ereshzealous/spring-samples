package com.eresh.graphql.service;

import com.eresh.graphql.persistence.entity.Author;
import com.eresh.graphql.persistence.repository.AuthorRepository;
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
public class AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	@GraphQLMutation(name = "saveAuthor")
	public Author saveAuthor(@GraphQLArgument(name = "author") Author author) {
		if (StringUtils.isBlank(author.getId())) {
			Author authorData = authorRepository.findByEmailId(author.getEmailId());
			if (authorData != null) {
				throw new RuntimeException("Email Already Exists");
			}
		}
		return authorRepository.save(author);
	}
}
