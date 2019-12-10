package com.eresh.graphql.util;

import com.eresh.graphql.persistence.entity.Author;
import com.eresh.graphql.ws.WSAuthor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created By Gorantla, Eresh on 09/Dec/2019
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FactoryClassUtil {

	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
	                                                                  .withZone(ZoneId.of("UTC"));

	public static WSAuthor toWSAuthor(Author author) {
		WSAuthor wsAuthor = new WSAuthor();
		wsAuthor.setAge(author.getAge());
		wsAuthor.setCreatedDate(author.getCreatedDate() != null ? author.getCreatedDate()
		                                                                .format(timeFormatter) : null);
		wsAuthor.setDateOfBirth(author.getDateOfBirth());
		wsAuthor.setEmailId(author.getEmailId());
		wsAuthor.setMobileNumber(author.getMobileNumber());
		wsAuthor.setId(author.getId());
		wsAuthor.setName(author.getName());
		wsAuthor.setUpdatedDate(author.getUpdatedDate() != null ? author.getUpdatedDate()
		                                                                .format(timeFormatter) : null);
		return wsAuthor;
	}
}
