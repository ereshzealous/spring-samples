package com.eresh.graphql.service;

import com.eresh.graphql.dto.PageInfo;
import com.eresh.graphql.enums.SortOrder;
import com.eresh.graphql.persistence.entity.Author;
import com.eresh.graphql.persistence.repository.AuthorRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By Gorantla, Eresh on 03/Dec/2019
 **/
@Service
@GraphQLApi
public class AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	private static final String DEFAULT_SORT_PROPERTY = "id";

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

	@GraphQLQuery(name = "allAuthors")
	public List<Author> getAuthors(@GraphQLArgument(name = "page") @Valid PageInfo info) {
		Sort sort = Sort.by(SortOrder.ASC.equals(info.getSortOrder()) ? Sort.Direction.ASC : Sort.Direction.DESC,
		                    StringUtils.isNotBlank(info.getSortField()) ? info.getSortField() : DEFAULT_SORT_PROPERTY);
		Pageable pageable = PageRequest.of(info.getPage(), info.getSize(), sort);
		Page<Author> authorPage = authorRepository.findAll(pageable);
		return authorPage != null ? authorPage.getContent() : new ArrayList<>();
	}
}
