package com.eresh.graphql.service;

import com.eresh.graphql.persistence.entity.Author;
import com.eresh.graphql.persistence.entity.BookDetails;
import com.eresh.graphql.persistence.entity.Language;
import com.eresh.graphql.persistence.entity.Publication;
import com.eresh.graphql.persistence.repository.AuthorRepository;
import com.eresh.graphql.persistence.repository.BookDetailsRepository;
import com.eresh.graphql.persistence.repository.LanguageRepository;
import com.eresh.graphql.persistence.repository.PublicationRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Gorantla, Eresh on 03/Dec/2019
 **/
@Service
@GraphQLApi
public class BookDetailsService {

	@Autowired
	BookDetailsRepository bookDetailsRepository;

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	LanguageRepository languageRepository;

	@Autowired
	PublicationRepository publicationRepository;

	@GraphQLQuery(name = "books")
	public List<BookDetails> getBooks() {
		return bookDetailsRepository.findAll();
	}

	@GraphQLQuery(name = "book")
	public BookDetails getBookDetails(@GraphQLArgument(name = "id") String id) {
		return bookDetailsRepository.getOne(id);
	}

	@GraphQLMutation(name = "saveBook")
	@Transactional
	public BookDetails saveBookDetails(@GraphQLArgument(name = "book") BookDetails bookDetails) {
		BookDetails savedBookDetails = null;

		List<Author> authors = authorRepository.findByIdIn(bookDetails.getAuthors()
		                                                              .stream()
		                                                              .map(Author::getId)
		                                                              .collect(Collectors.toList()));

		List<Publication> publications = publicationRepository.findByIdIn(bookDetails.getPublications()
		                                                                             .stream()
		                                                                             .map(Publication::getId)
		                                                                             .collect(Collectors.toList()));

		List<Language> languages = languageRepository.findByIdIn(bookDetails.getLanguages()
		                                                                    .stream()
		                                                                    .map(Language::getId)
		                                                                    .collect(Collectors.toList()));
		bookDetails.setAuthors(authors);
		bookDetails.setLanguages(languages);
		bookDetails.setPublications(publications);
		return bookDetailsRepository.save(bookDetails);
	}

}
