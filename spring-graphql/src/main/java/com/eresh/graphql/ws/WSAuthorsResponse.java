package com.eresh.graphql.ws;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Gorantla, Eresh on 09/Dec/2019
 **/
@Getter
@Setter
@NoArgsConstructor
public class WSAuthorsResponse {
	private Integer totalRecords;
	private List<WSAuthor> authors = new ArrayList<>();

	public WSAuthorsResponse(Integer totalRows, List<WSAuthor> authors) {
		this.authors = authors;
		this.totalRecords = totalRows;
	}
}
