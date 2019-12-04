package com.eresh.graphql.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By Gorantla, Eresh on 03/Dec/2019
 **/
@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class BookDetails {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Column(name = "book_name")
	private String bookName;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> authors = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "book_publications", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "publication_id"))
	private List<Publication> publications = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "book_languages", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "language_id"))
	private List<Language> languages = new ArrayList<>();

	@Column(name = "created_date", updatable = false)
	private ZonedDateTime createdDate = ZonedDateTime.now();

	@UpdateTimestamp
	@Column(name = "updated_date", insertable = false)
	private ZonedDateTime updatedDate;
}
